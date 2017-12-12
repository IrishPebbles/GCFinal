package com.gc.util;

import java.util.ArrayList;
import java.util.List;

import com.gc.dao.AttendeesDaoImpl;
import com.gc.dao.OutingDaoImpl;
import com.gc.dao.SurveyDao;
import com.gc.dao.SurveyDaoImpl;
import com.gc.dto.AttendeesDto;
import com.gc.dto.OutingDto;
import com.gc.dto.PersonDto;
import com.gc.dto.SurveyDto;

public class Survey {

	private ArrayList<Person> voters;
	private ArrayList<String> potentialVenues;// 5 items that are strings that reference a zomato id
	private Integer[] voteScore;
	private String surveyID;
	private int sIntID;

	public Survey() {
		// TODO Auto-generated constructor stub
	}

	public void updateVotes(int index) {
		voteScore[index]++;
	}

	public Survey(SurveyDto DtoObj) {
		// Below creates an arraylist to store Restaurant ID's which are then inserted
		potentialVenues = new ArrayList<String>();
		potentialVenues.add(DtoObj.getOptVenueID1());
		potentialVenues.add(DtoObj.getOptVenueID2());
		potentialVenues.add(DtoObj.getOptVenueID3());
		potentialVenues.add(DtoObj.getOptVenueID4());
		potentialVenues.add(DtoObj.getOptVenueID4());
		// Below creates an array that contains the votes taken
		voteScore = new Integer[5];
		voteScore[0] = DtoObj.getVoteCount1();
		voteScore[1] = DtoObj.getVoteCount2();
		voteScore[2] = DtoObj.getVoteCount3();
		voteScore[3] = DtoObj.getVoteCount4();
		voteScore[4] = DtoObj.getVoteCount5();
	}

	public Survey(String id) {
		this.surveyID = id;
		potentialVenues = new ArrayList<String>();
	}

	public Integer[] getVoteScore() {
		return voteScore;
	}

	public void setVoteScore(Integer[] voteScore) {
		this.voteScore = voteScore;
	}

	public String getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(String surveyID) {
		this.surveyID = surveyID;
	}

	public ArrayList<String> getPotentialVenues() {
		return potentialVenues;
	}

	public void setPotentialVenues(ArrayList<String> potentialVenues) {
		this.potentialVenues = potentialVenues;
	}

	public void setVoters(ArrayList<Person> voters) {
		this.voters = voters;
	}

	private ArrayList<Integer> numVotes;

	public ArrayList<Person> getVoters() {
		return voters;
	}

	public int updateScore() {
		return 0;
	}

	public RestaurantObj finalResult() {
		return null;
	}

	public boolean votingHasEnded() {
		return false;
	}

	public ArrayList<Integer> getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(ArrayList<Integer> numVotes) {
		this.numVotes = numVotes;
		// Write voters to db
	}

	public void createPotentialList(GeolocationAPI location, Outing parent) {
		ZoomatoAPI zApi = new ZoomatoAPI(location);
		SurveyDao sdao = new SurveyDaoImpl();
		potentialVenues = zApi.getList();
		// This is where the arraylist is created that contains five rest ids that i am
		// immediately injecting into a survey row
		String surveyID = parent.getSurveyID(); // this is a really not so great idea but TEMPORARY
		sdao.addSurvey(surveyID, potentialVenues.get(0), potentialVenues.get(1), potentialVenues.get(2),
				potentialVenues.get(3), potentialVenues.get(4), 0, 0, 0, 0, 0, false);
		// we need to access a surveyID to let the outing object / table know, but we
		// don't have any primary key we assign, I think we should look at a composite

	}

	public String buildVotingeRestaurantTable(String surveyID) {
		String tableHtml = "<h1> Welcome to the event ! </h1>" + "<h3> Please vote below</h3>"
				+ "<h5>You may vote for more than one choice. Each vote will be weighted equally</h5>"
				+ "	<form action=\"recordVote\" method =\"get\">" + "	<table border=\"1\">";
		tableHtml += "<input type=\"hidden\" name=\"surveyID\" value=\"" + surveyID + "\">";// this allows us to pass
																							// the key
		RestaurantObj placeholder;
		for (int i = 0; i < 5; i++) {
			placeholder = ZoomatoAPI.searchByRestID(potentialVenues.get(i));

			tableHtml += "	<tr><td> <input type=\"checkbox\" name=\"rstrnt\" value=\"" + placeholder.getRestName()
					+ "\" >" + " <a href=\" " + placeholder.getRestURL() + "\">" + placeholder.getRestName() + "</a>"
					+ "</td><td> Rating:" + placeholder.getRestRating() + "</td>\n</tr>";
		}

		tableHtml += "</table> " + "<input type=\"submit\" value=\"Vote\" > </form>";
		return tableHtml;
	}

	public String buildResultRestaurantTable(String[] restaurantVote) {

		String tableHtml = "<h1> Welcome to the event ! </h1>"
				+ "<h3> Thank you for voting!</h3> <h5> Here is what you voted for</h3>" + "<table border=\"1\">";
		for (int i = 0; i < restaurantVote.length; i++) {

			tableHtml += "	<tr> " + "<td>  " + restaurantVote[i] + "</td>	</tr>";//
		}
		tableHtml += "</table> ";
		return tableHtml;
	}

	// updates the vote tallies in the database, based on each attendee
	public void votingMethod(String[] rstrntNames, String surveyID, SurveyDto surveyDto, SurveyDaoImpl surveyDB) {

		ArrayList<String> arList = new ArrayList<String>();

		arList = getPotentialVenues();// gets the list of outing venues from the database

		RestaurantObj placeholder;

		if (rstrntNames != null) {
			for (int i = 0; i < rstrntNames.length; i++) {
				for (int j = 0; j < 5; j++) {
					placeholder = ZoomatoAPI.searchByRestID(arList.get(i));
					String restName = placeholder.getRestName();

					if (restName.equals(rstrntNames[i])) {
						updateVotes(j);
					}
				}
			}

			Integer[] voteScore = getVoteScore();

			surveyDto.setVoteCount1(voteScore[0]);
			surveyDto.setVoteCount2(voteScore[1]);
			surveyDto.setVoteCount3(voteScore[2]);
			surveyDto.setVoteCount4(voteScore[3]);
			surveyDto.setVoteCount5(voteScore[4]);

			surveyDB.updateSurvey(surveyDto);
		}
	}

	public boolean attendeeCanVote(String emailAddress, String surveyID) {
		AttendeesDaoImpl aDao = new AttendeesDaoImpl();
		PersonDto pDto = new PersonDto();
		OutingDaoImpl oDto = new OutingDaoImpl();
		pDto.setUserEmail(emailAddress);
		int userId = pDto.getUserID();

		List<AttendeesDto> attendeeList = new ArrayList<AttendeesDto>();
		attendeeList = aDao.searchID(userId);

		List<OutingDto> outingList = new ArrayList<OutingDto>();
		outingList = oDto.searchSurveyID(surveyID);

		int outingID = outingList.get(0).getOutingID();

		if (!attendeeList.get(0).getVoted()) {

			for (int i = 0; i < attendeeList.size(); i++) {
				if (outingID == attendeeList.get(i).getOutingID()) {
					attendeeList.get(i).setVoted(true);
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Survey [voters=" + voters + ", potentialVenues=" + potentialVenues + "]";
	}

}
