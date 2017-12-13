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

@SessionAttributes({"authenticated", "username"})
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
			@RequestParam("emailAddress") String emailAddress,
			@RequestParam("street") String street, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("outingName") String outingName,
			@RequestParam("date") String date, Model model)
			throws ParseException, AddressException, MessagingException {


		// creating the daoImpl to write to the database
		PersonDao pdao = new PersonDaoImpl();
		OutingDao outDao = new OutingDaoImpl();
		AttendeesDao  attendDao = new AttendeesDaoImpl();
		
		ArrayList<PersonDto> user  =  (ArrayList<PersonDto>) pdao.searchByEmail(organizerEmail); // we need to enter if statement to count for userEmail not found
		System.out.println(user + "  user is empty "+ user.isEmpty());
		String userLoginText = "";
		if (!user.isEmpty()) {
			model.addAttribute("user",organizerEmail);
			
			PersonDto searchUser = user.get(0); // getting userEmail from ArrayList<PersonDto> at location zero
			userLoginText =" <h2> Welcome " + organizerEmail+ "</h2> Your user name: <input type=\"email\" name=\"username\"value=\"" + organizerEmail + "\"><br><br> Please enter your password:  <input type=\"password\"name=\"passwordBox1\">";
			
		}
			
		else {
		userLoginText = "<p > You do not have an account associated with  "+ organizerEmail + ". </p>" +"Please create an account below: </p> Your user name: <input type=\"email\"name=\"username\"value=\"" + organizerEmail + "\"><br><br> Please enter your password:     <input type=\"password\"name=\"passwordBox1\"><br> <br> Please Re-enter password your: <input type=\"password\"name=\"passwordBox2\"> <br><br> "; 
			
			// what we want to do if they don't have account created. 
			pdao.addPerson(organizerEmail, "1");
			}	
		
		//System.out.println(warning);
		//model.addAttribute("userResult", userLogin);
		

		// Changes input java date into sql date
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]), Integer.parseInt(formatDate[2]));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		// Adding people coming from the form into relevant databases
		//// we need the id of this organizer for the next push to the
														// database
		//fix after lunch -- we need to check again if user exists
		int organizerId = pdao.searchByEmail(organizerEmail).get(0).getUserID();// we need to be able to search a person
						// a different field

		String surveyID = outingName + "," + date.toString() + "," + organizerId;// syntax for key

		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the
		System.out.println(Arrays.toString(emailAddresses));
		
		
		
		GeolocationAPI location = new GeolocationAPI(street, city, state);
		// passing location to create and return survey-- I dont know that we ever use this outing object?
		Outing constructingOuting = new Outing(outingName, sqlDate, null, null, location, surveyID);

		// this gets the list of potential Restaurants
		Survey mySurvey = constructingOuting.getPotentialEvent();
		
		outDao.addOuting(outingName, surveyID, eventDate, " ", organizerId);
		// this builds the HTML OBJ table for voting
		
		
		
		// Creates email generator object and sends the emails upon clicking submit on
		// the preferences page.
		
		  EmailGenerator email = new EmailGenerator(); 
		  String votingLink = "";
		  for(int i =0; i < emailAddresses.length; ++i) { 
			  votingLink =" http://localhost:8080/GCFinal/emailLink?surveyID=" + surveyID + "&voterEmail="+ emailAddresses[i];
			  email.generateAndSendEmail(organizerEmail,emailAddresses[i], votingLink); 
		  }

		// this gets the list of potiential Restaurants		
	      int outingID = outDao.searchSurveyID(surveyID).get(0).getOutingID();
		  for (int i = 0; i < emailAddresses.length; i++) {
			  user  =  (ArrayList<PersonDto>) pdao.searchByEmail(emailAddresses[i]); // we need to enter if statement to count for userEmail not found
			 System.out.println(user + "  user is empty "+ user.isEmpty());
			 if (!user.isEmpty()) {
					pdao.addPerson(emailAddresses[i], "1");
					
			}
			 int personID = pdao.searchByEmail(emailAddresses[i]).get(0).getUserID();
			//write to the attendees database		
			 attendDao.addNewAttendees(personID, outingID);
		}
		  
		  
		 
		
		//this is where we need to output the HTML for logging
		
		// this builds the HTML OBJ table for voting
		String outingObjHTML = "<h2> " + outingName + "</h2>";
		outingObjHTML += "<h4> " + date + "</h4>";
		outingObjHTML += "<form action=\"recordVote\" method =\"get\">" ;
		outingObjHTML += userLoginText;
		// this method builds the voting form we need to tell it the SurveyID
		outingObjHTML += mySurvey.buildVotingeRestaurantTable(surveyID, organizerEmail);
		outingObjHTML += "<input type=\"submit\" value=\"Vote\" > </form>";
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

	@RequestMapping(value="emailLink", method=RequestMethod.GET)
	public ModelAndView buildVotePage(Model model, @RequestParam("surveyID") String surveyID, @RequestParam("voterEmail") String voterEmail) {

		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		OutingDaoImpl outingDB = new OutingDaoImpl();
		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0); // this gets the row record from the data for this
		// survey
		OutingDto outingDto = outingDB.searchSurveyID(surveyID).get(0);
		Survey mySurvey = new Survey(surveyDto);


		String outingObjHTML = "<h2> " + outingDto.getOutingName() + "</h2>";
		outingObjHTML += "<h4> " + outingDto.getDateOfEvent().getMonth() + outingDto.getDateOfEvent().getDay() + outingDto.getDateOfEvent().getYear()+ "</h4>";
		outingObjHTML += "<form action=\"recordVote\" method=\"get\">";
		outingObjHTML += mySurvey.buildVotingeRestaurantTable(surveyID, voterEmail);
		outingObjHTML += "<input type=\"submit\" value=\"Vote\" > </form>";
			
		return new ModelAndView("voting", "result", outingObjHTML);
	}

	@RequestMapping(value = "/recordVote", method = RequestMethod.GET)
	public ModelAndView recordVote(Model model, @RequestParam("voterEmail") String voterEmail,
			@RequestParam("surveyID") String surveyID, @RequestParam("rstrnt") String[] restaurantVote, @RequestParam("username") String userName,
			@RequestParam("passwordBox1") String pass1) {
			PersonDaoImpl addUser = new PersonDaoImpl();
			
			//if the voterEmail have an account where we have added a " " as the password
			if(addUser.searchByEmail(voterEmail).get(0).getUserPassword().equals("1")) {
				
				String passHash = Person.generateHashPassword(pass1);
		
				addUser.addPerson(userName, passHash);
		
			}
			else {
				//validate user password
			}
		
		
				SurveyDaoImpl surveyDB = new SurveyDaoImpl();
				// we have to know who voter is
				// If you are using a build link it has to be formatted with no quotes
				SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0); // this gets the row record from the data for this
				Survey mySurvey = new Survey(surveyDto);// we build a survey object FROM the row in the database

				// SurveyDto holds results from survey so that we can manipulate them. See
				// Survey class to see organization

				mySurvey.votingMethod(restaurantVote, surveyDto, surveyDB);
				String outingObjHTML = "";
				outingObjHTML = mySurvey.buildResultRestaurantTable(restaurantVote);// when we have the object built

				// TODO update the Out object with how many people 
				// we should search the database for the surveyID
				
				/* This will work once the attendees are filled
				if (mySurvey.attendeeCanVote(voterEmail, surveyID)) {


				mySurvey.votingMethod(restaurantVote, surveyDto, surveyDB);

				// TODO get the Outing information: Event Name, Organizer, Date from the outing
				// object, if we are searching by ID by doing a join on the table
				// I tried some SQL queries but we will need help

					outingObjHTML = "<h2> Thank you " + voterEmail + " </h2> <h3> Please vote below: " + surveyID + "</h3>";
					outingObjHTML = mySurvey.buildVotingeRestaurantTable(surveyID, voterEmail);// when we have the object built we may not
																			// need to pass an array
					// TODO call a method to set the email address
				} else {
					outingObjHTML = "<h2> Thank you " + voterEmail + " </h2> <h3> You have already voted </h3>";
				}*/

				return new ModelAndView("voting", "result", outingObjHTML);
	}

	// TODO needs to be working -- we may have to push a outing variable in a hidden
	// field // we need to make another hidden field to record who is voting



	

	
	@RequestMapping(value = "/addnewuserinfo", method = RequestMethod.POST)
	public ModelAndView recordUserToDB(Model model, @RequestParam("username") String userName,
			@RequestParam("passwordBox1") String pass1) {
		
		PersonDaoImpl addUser = new PersonDaoImpl();
		
		String passHash = Person.generateHashPassword(pass1);

		addUser.addPerson(userName, passHash);

		String outingObjHTML = "<h2> Thank you " + userName + " </h2> <h3> Please vote below: " + passHash + "</h3>";
		

		return new ModelAndView("voting", "userResult", outingObjHTML);
	}

	// TODO needs to be working -- we may have to push a outing variable in a hidden
	// field // we need to make another hidden field to record who is voting
	/*@RequestMapping("/needstobemerged")
	public ModelAndView recordVote(Model model, @RequestParam("rstrnt") String[] restaurantVote,
			@RequestParam("surveyID") String surveyID, @RequestParam("username") String userName,
			@RequestParam("passwordBox1") String pass1) {
		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		PersonDaoImpl addUser = new PersonDaoImpl();	
		String passHash = Person.generateHashPassword(pass1);
		addUser.addPerson(userName, passHash);

		// surveyID should be filled from the database- is not right now.
	
		// we have to know who voter is
		
		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0); // this should be filled from the database
		System.out.println(
				" Survey DTO  restaurant ID" + surveyDto.getOptVenueID1() + " vote count " + surveyDto.getVoteCount1());

		Survey mySurvey = new Survey(surveyDto);
		String outingObjHTML = mySurvey.buildResultRestaurantTable(restaurantVote);// when we have the object built we
																					// may not need to pass an array
		// get survey object (from Outing object)

		// update the object
		// let the person know they have voted

		return new ModelAndView("voting", "result", outingObjHTML);
	}
	*/
	
	@RequestMapping(value = "/indexlogin", method = RequestMethod.POST)
	public ModelAndView loginCustomer(@RequestParam("userEmail") String username,Model model)throws ClassNotFoundException, SQLException {
		PersonDaoImpl database = new PersonDaoImpl();
	
		ArrayList<PersonDto> user  =  (ArrayList<PersonDto>) database.searchByEmail(username); // we need to enter if statement to count for userEmail not found
		System.out.println(user + "  user is empty "+ user.isEmpty());
		String warning = null;
		//if the person is in the database
		if (!user.isEmpty()) {
			model.addAttribute("user",username);
			
			PersonDto searchUser = user.get(0); // getting userEmail from ArrayList<PersonDto> at location zero
			warning =" Welcome " + username;
			//we just let them vote
			
		}
		// they need to create password	
		else {
			warning = "<p class='warning'> You do not have an account associated with  "+ username +
					"<form action=\"addnewuserinfo\" method=\"post\">" +
					"Please create an account below: </p> Your user name: <input type=\"email\"name=\"username\"value=\"" + username + "\"><br><br> Please enter your password:     <input type=\"password\"name=\"passwordBox1\"><br> <br> Please Re-enter password your: <input type=\"password\"name=\"passwordBox2\"> <br><br>  <input type=\"submit\"value=\"Submit\"> "; 
			
			//return new ModelAndView("voting", "userName", warning);
			// what we want to do if they don't have account created. 
			
		}	
		
		//System.out.println(warning);
		model.addAttribute("authenticated", username);
		return new ModelAndView("voting", "result", warning);
		
		//return new ModelAndView("preferences", "noAccountMessage", warning);
	}
	@RequestMapping("preferences")
	public String viewPreferencesPage() {
		// System.out.println("Here");
		return "preferences";
	}

	//we need to have it taking in an authenticated user, 
	@RequestMapping("/recordvote")
	public void determineAndViewFinalResults() {
		AttendeesDaoImpl attendeeDao = new AttendeesDaoImpl();
		SurveyDaoImpl surveyDao = new SurveyDaoImpl();
		SurveyDto surveyDTO = new SurveyDto();
		ZoomatoAPI grabInfoFromAPI = new ZoomatoAPI(); 
		RestaurantObj winningRestInfo = new RestaurantObj(); 

		// Here we pull in the survey once the column value "HasVoted" has been marked
		// true.
		// This triggers once the last participant has submitted their vote
		ArrayList<SurveyDto> finalSurvey = (ArrayList<SurveyDto>) surveyDao.searchSurvey("WeaselStompingDay,2017-12-21,97");
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








