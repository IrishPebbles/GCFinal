package com.gc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dao.AttendeesDao;
import com.gc.dao.AttendeesDaoImpl;
import com.gc.dao.CurrentScoreDao;
import com.gc.dao.CurrentScoreDaoImpl;
import com.gc.dao.OutingDao;
import com.gc.dao.OutingDaoImpl;
import com.gc.dao.PersonDao;
import com.gc.dao.PersonDaoImpl;
import com.gc.dao.SurveyDao;
import com.gc.dao.SurveyDaoImpl;
import com.gc.dto.CurrentScoreDto;
import com.gc.dto.SurveyDto;
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.RestaurantObj;
import com.gc.util.Survey;
import com.gc.util.ZoomatoAPI;

@Controller
public class HomeController {

	@RequestMapping({ "/", "index" })
	public ModelAndView homepage(Model model) {
		CurrentScoreDto dto = new CurrentScoreDto();
		CurrentScoreDao dao = new CurrentScoreDaoImpl();
		AttendeesDao adao = new AttendeesDaoImpl();
		OutingDao odao = new OutingDaoImpl();
		PersonDao pdao = new PersonDaoImpl();
		SurveyDao sdao = new SurveyDaoImpl();
		model.addAttribute("displayPreference", "\"display:none;\"");

		determineAndViewFinalResults();
		return new ModelAndView("index", "result", "");

	}

	@RequestMapping(value = "voting", method = RequestMethod.POST)
	public ModelAndView votingGeneration(@RequestParam("organizerEmail") String organizerEmail,
			@RequestParam("emailAddress") String emailAddress, @RequestParam("passwordInformation") String userPassword,
			@RequestParam("street") String street, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("outingName") String outingName,
			@RequestParam("date") String date, Model model)
			throws ParseException, AddressException, MessagingException {

		// creating the daoImpl to write to the database
		PersonDao pdao = new PersonDaoImpl();
		OutingDao outDao = new OutingDaoImpl();

		// Changes input java date into sql date
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]),
				Integer.parseInt(formatDate[2]));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		// Adding people coming from the form into relevant databases
		pdao.addPerson(organizerEmail, userPassword);// we need the id of this organizer for the next push to the
														// database
		int organizerId = pdao.searchByEmail(organizerEmail).get(0).getUserID();// we need to be able to search a person
		String surveyID = outingName + "," + date.toString() + "," + organizerId;// creates a survey ID from collected
																					// information

		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the

		// right now we are not adding attendees to the database
		/*
		 * for (int i = 0; i < emailAddress.length(); ++i) {
		 * //pdao.addPerson(emailAddresses[i], "3R5S");
		 * //System.out.println("first email" + emailAddresses[0]); }
		 */

		Person organizer = new Person(organizerEmail, "nope", null);// we may want the organizer's name
		attendees.add(organizer);// adding organizer to the attendee group because their email address comes from
									// a different field

		for (int i = 0; i < emailAddresses.length; i++) {
			attendees.add(new Person(emailAddresses[i], null, null));
			// we can drop the name req from the constructor OR get their name for oAuth OR
			// get it from the database
		}

		GeolocationAPI location = new GeolocationAPI(street, city, state);
		// passing location to create and return survey
		Outing constructingOuting = new Outing(outingName, sqlDate, organizer, attendees, location, surveyID);

		// this gets the list of potential Restaurants
		Survey mySurvey = constructingOuting.getPotentialEvent();

		// this writes it to the database
		outDao.addOuting(outingName, surveyID, eventDate, " ", organizerId);
		// this builds the HTML OBJ table for voting
		String outingObjHTML = "<h2> " + outingName + "</h2>";
		outingObjHTML += "<h4> " + date + "</h4>";
		// this method builds the voting form we need to tell it the SurveyID
		outingObjHTML += mySurvey.buildVotingeRestaurantTable(surveyID);
		// Creates email generator object and sends the emails upon clicking submit on
		// the preferences page.
		/*
		 * EmailGenerator email = new EmailGenerator(); for(int i =0; i <
		 * emailAddresses.length; ++i) { email.generateAndSendEmail(organizerEmail,
		 * emailAddresses[i]); }
		 */

		return new ModelAndView("voting", "result", outingObjHTML);
	}

	// TODO This method receives the clickable link
	@RequestMapping(value = "/voting", method = RequestMethod.GET)
	public ModelAndView recordVoteFromLink(Model model, @RequestParam("voterEmail") String voterEmail,
			@RequestParam("surveyID") String surveyID) {
		System.out.println("Made it to this method");
		// we should search the database for the surveyID
		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		// LINK HAS TO BE FORMATTED WITH NO QUOTES :O
		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0); // this should be filled from the database
		// we build the survey object from the ID
		Survey mySurvey = new Survey(surveyDto);

		// TODO get the Outing information: Event Name, Organizer, Date from the outing
		// object, if we are searching by ID by doing a join on the table
		// I tried some SQL queries but we will need help

		String outingObjHTML = "<h2> Thank you " + voterEmail + " </h2> <h3> Please vote below: " + surveyID + "</h3>";
		outingObjHTML = mySurvey.buildVotingeRestaurantTable(surveyID);// when we have the object built we may not need
																		// to pass an array
		// TODO call a method to set the email address

		return new ModelAndView("voting", "result", outingObjHTML);
	}

	// TODO needs to be working -- we may have to push a outing variable in a hidden
	// field // we need to make another hidden field to record who is voting

	@RequestMapping("/recordVote")
	public ModelAndView recordVote(Model model, @RequestParam("rstrnt") String[] restaurantVote,
			@RequestParam("surveyID") String surveyID) {
		System.out.println("hello" + restaurantVote.toString() + " " + restaurantVote[0] + " " + restaurantVote[1] + " "
				+ restaurantVote[2] + " " + restaurantVote[3] + " " + restaurantVote[4] + " ");
		// surveyID should be filled from the database
		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		// we have to know who voter is
		String userEmail = "jenna.otto@gmail.com";// this is the organizer, needs to be a variable

		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0); // this gets the row record from the data for this
																		// survey

		Survey mySurvey = new Survey(surveyDto);// we build a survey object FROM the row in the database
		// SurveyDto holds results from survey so that we can manipulate them. See
		// Survey class to see organization

		// TODO In progress write a survey method, that check the array to see who has
		// voted

		mySurvey.votingMethod(restaurantVote, surveyID, surveyDto, surveyDB);

		String outingObjHTML = mySurvey.buildResultRestaurantTable(restaurantVote);// when we have the object built

		// TODO update the OUt object with how many people have left to vote
		// TODO let the person know they have voted

		return new ModelAndView("voting", "result", outingObjHTML);
	}

	@RequestMapping("preferences")
	public String viewPreferencesPage() {

		return "preferences";
	}

	 //@RequestMapping("/recordvote")
	public void determineAndViewFinalResults() {
		AttendeesDaoImpl attendeeDao = new AttendeesDaoImpl();
		SurveyDaoImpl surveyDao = new SurveyDaoImpl();
		SurveyDto surveyDTO = new SurveyDto();
		ZoomatoAPI grabInfoFromAPI = new ZoomatoAPI(); 
		RestaurantObj winningRestInfo = new RestaurantObj(); 

		// Here we pull in the survey once the column value "HasVoted" has been marked
		// true.
		// This triggers once the last participant has submitted their vote
		ArrayList<SurveyDto> finalSurvey = (ArrayList<SurveyDto>) surveyDao
				.searchSurvey("WeaselStompingDay,2017-12-21,97");
		surveyDTO = finalSurvey.get(0);
		// Above I assign the arraylist the survey arrives in into an object for
		// manipulation

		ArrayList<Integer> voteCountArray = new ArrayList();
		voteCountArray.add(surveyDTO.getVoteCount1());
		voteCountArray.add(surveyDTO.getVoteCount2());
		voteCountArray.add(surveyDTO.getVoteCount3());
		voteCountArray.add(surveyDTO.getVoteCount4());
		voteCountArray.add(surveyDTO.getVoteCount5());
		// Here I'm assigning the numbers of votes and venue IDs into corresponding
		// positions in two different arrays
		ArrayList<String> venueArray = new ArrayList();
		venueArray.add(surveyDTO.getOptVenueID1());
		venueArray.add(surveyDTO.getOptVenueID2());
		venueArray.add(surveyDTO.getOptVenueID3());
		venueArray.add(surveyDTO.getOptVenueID4());
		venueArray.add(surveyDTO.getOptVenueID5());

		int temp = 0;
		String venue = "";
		// This "for" loop cycles through the voteCountArray list and finds the highest
		// vote count.
		// It also returns the restaurant ID of the corresponding entry from venueArray.
		for (int i = 0; i < voteCountArray.size(); i++) {
			if (voteCountArray.get(i) > temp) {
				temp = voteCountArray.get(i);
				venue = venueArray.get(i).toString();
			}
		}
		
		
		//Here I call the api and put in the winning venue's restID
		//I then assign it to a local Restaurant object, which is how I present the information.
		winningRestInfo = grabInfoFromAPI.searchByRestID(venue); 
		System.out.println("And the winner is: " + winningRestInfo.getRestName() );

	//TODO play with boolean values for determining whether or not people have voted
		//TODO figure out how to join the tables attendees and survey so we can call from both
		//TODO Write the logic that switches a survey to finished and triggers the final count and display

	
		}
	

	}


