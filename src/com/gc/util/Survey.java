package com.gc.util;
import java.util.ArrayList;

import com.gc.dao.SurveyDao;
import com.gc.dao.SurveyDaoImpl;

public class Survey {

	private ArrayList<Person> voters;
	private ArrayList<String> potentialVenues;// 5 items that are strings that reference a zomatoe id
	private Integer[] voteScore;
	private int surveyID;
	
	
	public Survey() {
		// TODO Auto-generated constructor stub
	}
	
	public Survey(String id) {
		this.surveyID = Integer.parseInt(id);
		potentialVenues = new ArrayList<String>();
	}
	public Integer[] getVoteScore() {
		return voteScore;
	}

	public void setVoteScore(Integer[] voteScore) {
		this.voteScore = voteScore;
	}

	public int getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(int surveyID) {
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
	
	public void createPotentialList(GeolocationAPI location) {
		ZoomatoAPI zApi = new ZoomatoAPI(location);
		SurveyDao sdao = new SurveyDaoImpl(); 
		potentialVenues = zApi.getList();
		//This is where the arraylist is created that contains five rest ids that i am immediatly injecting into a survey row
		sdao.addSurvey(potentialVenues.get(0), potentialVenues.get(1), potentialVenues.get(2), potentialVenues.get(3), potentialVenues.get(4), 0, 0, 0, 0, 0, false);
		
		
	}
	
	public String buildVotingeRestaurantTable() {
		SurveyDao sdaodbconnection = new SurveyDaoImpl();
		//sdaodbconnection;
		return "";
	}
	
	public String buildResultRestaurantTable() {
		return "";
	}

	@Override
	public String toString() {
		return "Survey [voters=" + voters + ", potentialVenues=" + potentialVenues + "]";
	}

}
