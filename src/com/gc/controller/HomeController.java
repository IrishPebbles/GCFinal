package com.gc.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.tools.DocumentationTool.Location;

import org.hibernate.property.access.spi.BuiltInPropertyAccessStrategies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
import com.gc.dto.AttendeesDto;
import com.gc.dto.CurrentScoreDto;
import com.gc.dto.OutingDto;
import com.gc.dto.PersonDto;
import com.gc.dto.SurveyDto;
import com.gc.util.EmailGenerator;
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.RestaurantObj;
import com.gc.util.Survey;
import com.gc.util.ZoomatoAPI;

@SessionAttributes({ "authenticated", "username" })
@Controller
public class HomeController {
	// Homepage loading with HTML
	@RequestMapping({ "/", "index", "tryagain" })

	public ModelAndView homepage(Model model) {

		return new ModelAndView("index", "result", "");

	}

	// this the process that runs after someone hits submit on to create an outing

	@RequestMapping(value = "voting", method = RequestMethod.POST)
	public ModelAndView votingGeneration(@RequestParam("organizerEmail") String organizerEmail,
			@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("outingName") String outingName, @RequestParam("date") String date, Model model)
			throws ParseException, AddressException, MessagingException {

		// creating the daoImpl to write to the database
		PersonDao pdao = new PersonDaoImpl();
		OutingDao outDao = new OutingDaoImpl();
		AttendeesDao attendDao = new AttendeesDaoImpl();

		// validating user and returning the correct password fields for the user to
		// create an account or login
		String userLoginHTML = Person.checkUserGenerateHTML(organizerEmail);

		// If we wanted to track is a person to login with with the session ID
		// model.addAttribute("userResult", userLogin);

		// Changes input java date into sql date, this is the date that can be stored in
		// the database
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]),
				Integer.parseInt(formatDate[2]));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		// we need the Organizer ID from the table to write to the attendees tables
		int organizerId = pdao.searchByEmail(organizerEmail).get(0).getUserID();// we need to be able to search a person

		String surveyID = outingName + "," + date.toString() + "," + organizerId;// syntax for unique key

		// splits the emailAddress String into an array
		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the
		System.out.println(Arrays.toString(emailAddresses));

		GeolocationAPI location = new GeolocationAPI(street, city, state);
		// passing location to create and return survey-- we create this outingIbj to
		// build and create a survey
		Outing constructingOuting = new Outing(outingName, sqlDate, null, null, location, surveyID);

		// this gets the list of potential Restaurants that was creating int he OUting
		// Object
		Survey mySurvey = constructingOuting.getPotentialEvent();
		if(mySurvey.getPotentialVenues().isEmpty()) {
			String errorString = "<h3> We are sorry there was an issue with your address></h3>" +
					"<a href=\"index\">Try Again with a different address, the street is optional</a>";
			return new ModelAndView("voting", "result", errorString);
		}

		// writes the information to the table with finalLocation blank
		outDao.addOuting(outingName, surveyID, eventDate, " ", organizerId);

		// this gets the list of potiential Restaurants
		ArrayList<PersonDto> user;
		Person attendee;
		int outingID = outDao.searchSurveyID(surveyID).get(0).getOutingID();
		for (int i = 0; i < emailAddresses.length; i++) {
			attendee = Person.checkUserExistsOrCreate(emailAddresses[i]);

			int personID = attendee.getPersonID();
			// write to the attendees database
			attendDao.addNewAttendees(personID, outingID);

		}
		// add the organizer the attendees database as well
		attendDao.addNewAttendees(organizerId, outingID);

		// this is where we need to output the HTML for logging

		// this builds the HTML OBJ table for voting
		String outingObjHTML = "<h1>  Welcome to " + outingName + "</h1>";
		outingObjHTML += "<h4>  for " + date + "</h4>";

		outingObjHTML += "<h3> Please vote below</h3>"
				+ "<h6>You may vote for more than one choice. Each vote will be weighted equally</h6>";
		outingObjHTML += "<form action=\"recordVote\" method =\"get\">";
		
		outingObjHTML += " <input type=\"hidden\" name=\"lat\" value=\" " + location.getLatitude() + "\" >";
		outingObjHTML += " <input type=\"hidden\" name=\"long\" value=\" " + location.getLongitude() + "\" >";
		// this line for the form action is critcal for votes, user and password
		// validation

		outingObjHTML += userLoginHTML;
		// this method builds the voting form we need to tell it the SurveyID
		outingObjHTML += mySurvey.buildVotingeRestaurantTable(surveyID, organizerEmail);
		outingObjHTML += "<input type=\"submit\" value=\"Vote\" > </form>";

		// Creates email generator object and sends the emails upon clicking submit on
		// the preferences page.
		EmailGenerator email = new EmailGenerator();
		String votingLink = "";

		for (int i = 0; i < emailAddresses.length; ++i) {
			votingLink = "http://gcoutings.us-east-2.elasticbeanstalk.com/emailLink?surveyID=" + surveyID
					+ "&voterEmail=" + emailAddresses[i] + "&lat=" + location.getLatitude() + "&long="
					+ location.getLongitude();
			email.generateAndSendEmail(organizerEmail, emailAddresses[i], votingLink);
		}
		
		String checkOnVoters = "http://gcoutings.us-east-2.elasticbeanstalk.com/finalResult?surveyID=" +surveyID + "&requesterEmail=" + organizerEmail;
		email.generateAndSendOrganizerEmail(organizerEmail, checkOnVoters);

		return new ModelAndView("voting", "result", outingObjHTML);
	}
	


	// TODO This method receives the clickable link

	@RequestMapping(value = "emailLink", method = RequestMethod.GET)
	public ModelAndView buildVotePage(Model model, @RequestParam("surveyID") String surveyID,
			@RequestParam("voterEmail") String voterEmail, @RequestParam("lat") String latString,
			@RequestParam("long") String longString) {
		GeolocationAPI location = new GeolocationAPI(Double.parseDouble(latString), Double.parseDouble(longString));
		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		OutingDaoImpl outingDB = new OutingDaoImpl();
		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0); // this gets the row record from the data for this
																		// survey
		OutingDto outingDto = outingDB.searchSurveyID(surveyID).get(0);
		Survey mySurvey = new Survey(surveyDto);
		String userloginHTML = Person.checkUserGenerateHTML(voterEmail);

		String outingObjHTML = "<h1>  Welcome to" + outingDto.getOutingName() + "</h1>";
		outingObjHTML += "<h4> " + outingDto.getDateOfEvent().getMonth() + outingDto.getDateOfEvent().getDay()
				+ outingDto.getDateOfEvent().getYear() + "</h4>";
		outingObjHTML += "<form action=\"recordVote\" method=\"get\">";
		outingObjHTML += userloginHTML;
		outingObjHTML += "<h3> Voting</h3>"
				+ "<h6>Please vote for any restaurant where you woud be willing to eat:</h6>";
		outingObjHTML += " <input type=\"hidden\" name=\"lat\" value=\" " + location.getLatitude() + "\" >";
		outingObjHTML += " <input type=\"hidden\" name=\"long\" value=\" " + location.getLongitude() + "\" >";
		outingObjHTML += mySurvey.buildVotingeRestaurantTable(surveyID, voterEmail);
		outingObjHTML += "<input type=\"submit\" value=\"Vote\" > </form>";

		return new ModelAndView("voting", "result", outingObjHTML);
	}

	// in this method we are recording the users vote
	@RequestMapping(value = "/recordVote", method = RequestMethod.GET)
	public ModelAndView recordVote(Model model, @RequestParam("voterEmail") String voterEmail,
			@RequestParam("surveyID") String surveyID, @RequestParam("rstrnt") String[] restaurantVote) throws AddressException, MessagingException {
		PersonDaoImpl userList = new PersonDaoImpl();

		// if the voterEmail have an account where we have added a " " as the password
		PersonDto voter = userList.searchByEmail(voterEmail).get(0);
		if (voter.getUserPassword().equals("1")) {
			int userID = voter.getUserID();
			//String passHash = Person.generateHashPassword(pass1);
			//PersonDto personToUpdate = new PersonDto(userID, voterEmail, passHash);
			//userList.updatePassword(personToUpdate);

		} else {
			// validate user password
		}

		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		// we have to know who voter is
		// If you are using a build link it has to be formatted with no quotes
		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0);

		// this gets the row record from the data for this
		Survey mySurvey = new Survey(surveyDto);// we build a survey object FROM the row in the database

		// SurveyDto holds results from survey so that we can manipulate them. See
		// Survey class to see organization

		mySurvey.votingMethod(restaurantVote, surveyDto, surveyDB);

		hasAttendeeVoted(voterEmail, surveyID);
		int votersLeft = hasEveryoneVoted(surveyID);
		System.out.println();
		String outingObjHTML = "<h3> Thank you for voting!</h3> ";
		
		String winnerID = countVotesAndPickWinner(surveyID);
		System.out.println("Voters Left " + votersLeft);
		if (votersLeft == 0) {
			outingObjHTML += "<h4> You are the last voter, so here are the results for this outing. We will also send an email to all the participants. </h4>";
		}
		
		
		outingObjHTML += mySurvey.buildResultRestaurantTable(winnerID, votersLeft);// when we have the object built
		
		return new ModelAndView("finalResult", "result", outingObjHTML);
	}
	
	

	@RequestMapping("preferences")
	public String viewPreferencesPage() {
		// System.out.println("Here");
		return "preferences";
	}

	
	@RequestMapping(value = "finalResult", method = RequestMethod.GET)
	public ModelAndView buildfinalPage(Model model, @RequestParam("surveyID") String surveyID,
			@RequestParam("requesterEmail") String requesterEmail) {
		SurveyDaoImpl sdao = new SurveyDaoImpl();
		OutingDaoImpl odao = new OutingDaoImpl();
		PersonDaoImpl pdao = new PersonDaoImpl();
		OutingDto outInstance = odao.searchSurveyID(surveyID).get(0);
		SurveyDto mySurvey = sdao.searchSurvey(surveyID).get(0);
		
		int requesterID = pdao.searchByEmail(requesterEmail).get(0).getUserID();
		String outingObjHTML = "<h3> Results for " + outInstance.getOutingName() + " on " +outInstance.getDateOfEvent().getMonth() + "-"+ outInstance.getDateOfEvent().getDay()+ "-" + outInstance.getDateOfEvent().getYear() + "</h3>";
		if(mySurvey.getHasVoted()) {
			//display finalResult
			Survey surveyInst = new Survey(mySurvey);
			outingObjHTML += surveyInst.buildResultRestaurantTable(Integer.toString(mySurvey.getfinalVenueID()), 0);
		} else{ 
			if (hasEveryoneVoted(surveyID)<2) {
				outingObjHTML += "<p> There is " + hasEveryoneVoted(surveyID) +  " voter left. </p>";
			} else {
			outingObjHTML += "<p> There are " + hasEveryoneVoted(surveyID) +  " voters left. </p> ";
			}
			//System.out.println(" Request " + requesterID + " organizer " + outInstance.getOrganizer());
			if(requesterID==outInstance.getOrganizer()) {
				outingObjHTML+= "<form action=\"endVoting\" method=\"get\">" +
					 "<input type=\"hidden\" name=\"surveyID\" value=\"" + surveyID+ "\">" +
					"<input type=\"submit\"  value=\"End Voting\"> </form>";
			}	
		}
		//System.out.println(" HTML THAT IS SUPPOSED TO SHOW  " + outingObjHTML);
		return new ModelAndView("finalResult", "result", outingObjHTML); 
		
	}
	
	@RequestMapping("finalResult")
	public String resultPage() {
		return "finalResult";
	}
	
	@RequestMapping(value = "endVoting", method = RequestMethod.GET)
	public ModelAndView endtheVoting(Model model, @RequestParam("surveyID") String surveyID) throws AddressException, MessagingException {
		SurveyDaoImpl sdao = new SurveyDaoImpl();
		OutingDaoImpl odao = new OutingDaoImpl();
		OutingDto outInstance = odao.searchSurveyID(surveyID).get(0);
		SurveyDto mySurvey = sdao.searchSurvey(surveyID).get(0);
		Survey surveyData = new Survey(mySurvey);
		mySurvey.setHasVoted(true);
		String restID = countVotesAndPickWinner(surveyID);
		String htmlObj = "<h2> You have ended the voting, your attendees will be notified of the result.";
		htmlObj +="<h3> Results for " + outInstance.getOutingName() + " on " +outInstance.getDateOfEvent().getMonth() + "-"+ outInstance.getDateOfEvent().getDay()+ "-" + outInstance.getDateOfEvent().getYear() + "</h3>";
		htmlObj += surveyData.buildResultRestaurantTable(restID, 0);
		
		
		return new ModelAndView("finalResult", "result", htmlObj);
	}
	

	public String countVotesAndPickWinner(String surveyID) throws AddressException, MessagingException {
		AttendeesDaoImpl attendeeDao = new AttendeesDaoImpl();
		OutingDaoImpl outingDao = new OutingDaoImpl();
		SurveyDaoImpl surveyDao = new SurveyDaoImpl();
		SurveyDto surveyDTO = new SurveyDto();
		PersonDaoImpl pDao = new PersonDaoImpl();


		// Here we pull in the survey once the column value "HasVoted" has been marked
		// true.
		// This triggers once the last participant has submitted their vote

		surveyDTO = surveyDao.searchSurvey(surveyID).get(0);
		String venue = "";
		if (surveyDTO.getHasVoted() == true) {
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
			// This "for" loop cycles through the voteCountArray list and finds the highest
			// vote count.
			// It also returns the restaurant ID of the corresponding entry from venueArray.
			for (int i = 0; i < voteCountArray.size(); i++) {
				if (voteCountArray.get(i) > temp) {
					temp = voteCountArray.get(i);
					venue = venueArray.get(i).toString();
				}
			}
			surveyDTO.setfinalVenueID(Integer.parseInt(venue));
			surveyDao.updateSurvey(surveyDTO);
			EmailGenerator email = new EmailGenerator();
			
			ArrayList<AttendeesDto> attendeeListArray = (ArrayList<AttendeesDto>) attendeeDao.searchByOutingID(outingDao.searchSurveyID(surveyID).get(0).getOutingID());
			AttendeesDto attendeeInfo;
			
			for (int i = 0; i < attendeeListArray.size(); ++i) {
						attendeeInfo = attendeeListArray.get(i);
						PersonDto attendee =(PersonDto) pDao.getPerson(attendeeInfo.getPersonID()).get(0);
						String resultLink = "http://gcoutings.us-east-2.elasticbeanstalk.com/finalResult?surveyID=" + surveyID+ "&requesterEmail="+attendee.getUserEmail();
						email.generateAndSendFinalEmail("", attendee.getUserEmail(), resultLink);
			}

		} else {
			//System.out.println("Still waiting for votes");
		}

		// TODO figure out how to join the tables attendees and survey so we can call
		// from both
		// TODO Write the logic that switches a survey to finished and triggers the
		// final count and display
		
		
		//venue is the RestaurantID
		return venue;
	}
	

	public void hasAttendeeVoted(String voterEmail, String surveyID) {
		PersonDaoImpl personDAO = new PersonDaoImpl();
		PersonDto personDTO = new PersonDto();
		AttendeesDaoImpl attendeeDAO = new AttendeesDaoImpl();
		AttendeesDto attendeeDTO = new AttendeesDto();
		OutingDaoImpl outingDAO = new OutingDaoImpl();
		OutingDto outingDTO = new OutingDto();

		// below I create a person object so that we can access the attendee
		// information. We use the person DAO to search for the relevant
		// person by their email (they are created when their email is entered for the
		// first time). We then have it "get" the object out of
		// the array it is returned in.
		personDTO = personDAO.searchByEmail(voterEmail).get(0);
		//System.out.println("We got: " + personDTO.getUserEmail()); // test purposes TODO delete when ready

		// We need the outing ID as well, so we create an outing DTO to get it for us
		outingDTO = outingDAO.searchSurveyID(surveyID).get(0);
		//System.out.println("This outing id is " + outingDTO.getOutingID());
		int personID = personDTO.getUserID();
		int outingID = outingDTO.getOutingID();
		// use person id to find attendee
		attendeeDTO = attendeeDAO.searchByPersonIDAndOutID(personID, outingID).get(0);
		// above we instantiate the attendeeDTO object. We call the information using
		// the DAO that searches by Person ID, then plug in
		// the person ID by calling it from the person DTO. The get 0 is because the
		// attendeeDAO returns an attendee object in an array,
		// so we need to "get" it so it can be converted into the object we call
		// attendeeDTO
		// switch false to true
		//System.out.println("If he voted: " + attendeeDTO.getVoted());
		if (attendeeDTO.getVoted() == false) {
			attendeeDTO.setVoted(true);
		} else {
			//System.out.println("He already voted!");
		}

		attendeeDAO.updateAttendees(attendeeDTO);
		//System.out.println("The ID should be 95: " + attendeeDTO.getPersonID()); // test purposes TODO delete when ready

		// Here we set the value to true and send it to the database

	}

	public int hasEveryoneVoted(String surveyID) {
		OutingDaoImpl outingDAO = new OutingDaoImpl();
		OutingDto outingDTO = new OutingDto();
		AttendeesDaoImpl attendeeDAO = new AttendeesDaoImpl();
		AttendeesDto attendeeDTO = new AttendeesDto();
		SurveyDaoImpl surveyDAO = new SurveyDaoImpl();
		SurveyDto surveyDTO = new SurveyDto();
		int remainder = 0;
		// Here I'm creating an outing ID by searching using the surveyID passed to us
		outingDTO = outingDAO.searchSurveyID(surveyID).get(0);

		// I then create an arraylist to hold all of the attendees DTO's so that we can
		// check each one to see if they've voted
		ArrayList<AttendeesDto> voteCheckArray = (ArrayList<AttendeesDto>) attendeeDAO
				.searchByOutingID(outingDTO.getOutingID());

		// The for loop checks each attendeeDTO to see if they've voted. If they have,
		// it adds to a counter.
		int temp = 0;
		for (int i = 0; i < voteCheckArray.size(); i++) {
			attendeeDTO = voteCheckArray.get(i);
			if (attendeeDTO.getVoted() == true) {
				temp += 1;
			}
		}
		// At the end of the loop, it checks the value of the counter against the length
		// of the voteCheckArray (number of potential voters)
		// If they are equal, that means everyone has voted, so it calls the Survey dao
		// and carries out the process of changing the
		// Survey tables hasVoted to true, which triggers the final count method.
		if (temp == voteCheckArray.size()) {
			System.out.println("Vote Complete!");
			surveyDTO = surveyDAO.searchSurvey(surveyID).get(0);
			surveyDTO.setHasVoted(true);
			surveyDAO.updateSurvey(surveyDTO);

		} else {
			remainder = voteCheckArray.size() - temp;
			//System.out.println("Need " + remainder + " more votes!");
		}
		//System.out.println("Did it work? " + temp);
		// instantiate them into DTO's
		return remainder;
	}
}