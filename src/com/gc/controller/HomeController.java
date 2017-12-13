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
import com.gc.dto.PersonDto;
import com.gc.dto.SurveyDto;
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.Survey;

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

		return new ModelAndView("index", "result", "");

	}

	// Serhiy add @RequestParam("password") String password
	@RequestMapping(value = "voting", method = RequestMethod.POST)
	public ModelAndView votingGeneration(@RequestParam("organizerEmail") String organizerEmail,
			@RequestParam("emailAddress") String emailAddress, @RequestParam String userPassword,
			@RequestParam("street") String street, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("outingName") String outingName,
			@RequestParam("date") String date, Model model)
			/* @RequestParam("votingWindow") String votingWindow, */
			throws ParseException, AddressException, MessagingException {
		// creating the daoImpl to write to the database
		PersonDao pdao = new PersonDaoImpl();
		OutingDao outDao = new OutingDaoImpl();
		
		ArrayList<PersonDto> user  =  (ArrayList<PersonDto>) pdao.searchByEmail(organizerEmail); // we need to enter if statement to count for userEmail not found
		System.out.println(user + "  user is empty "+ user.isEmpty());
		String userLoginText = "<p>";
		if (!user.isEmpty()) {
			model.addAttribute("user",organizerEmail);
			
			PersonDto searchUser = user.get(0); // getting userEmail from ArrayList<PersonDto> at location zero
			userLoginText =" <h2> Welcome " + organizerEmail+ "</h2> Your user name: <input type=\"email\" name=\"username\"value=\"" + organizerEmail + "\"><br><br> Please enter your password:  <input type=\"password\"name=\"passwordBox1\">";
			
		}
			
		else {
		userLoginText = "<p class> You do not have an account associated with  "+ organizerEmail +
					"<form action=\"addnewuserinfo\" method=\"post\">" +
					"Please create an account below: </p> Your user name: <input type=\"email\"name=\"username\"value=\"" + organizerEmail + "\"><br><br> Please enter your password:     <input type=\"password\"name=\"passwordBox1\"><br> <br> Please Re-enter password your: <input type=\"password\"name=\"passwordBox2\"> <br><br>  <input type=\"submit\"value=\"Submit\"> "; 
			
			// what we want to do if they don't have account created. 
			
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
		pdao.addPerson(organizerEmail, userPassword);// we need the id of this organizer for the next push to the
														// database
		int organizerId = pdao.searchByEmail(organizerEmail).get(0).getUserID();// we need to be able to search a person
		String surveyID = outingName + "," + date.toString() + "," + organizerId;// syntax for key

		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the
		System.out.println(Arrays.toString(emailAddresses));
		
		//this needs to be updated
		Person organizer = new Person(organizerEmail, "nope");// we may want the organizer's name
		attendees.add(organizer);

		GeolocationAPI location = new GeolocationAPI(street, city, state);
		// passing location to create and return survey
		Outing constructingOuting = new Outing(outingName, sqlDate, organizer, attendees, location, surveyID);

		// this gets the list of potiential Restaurants
		Survey mySurvey = constructingOuting.getPotentialEvent();

		System.out.println();
		outDao.addOuting(outingName, surveyID, eventDate, " ", organizerId);
		
		/*
		
		
		
		for (int i = 0; i < emailAddresses.length; i++) {
			attendees.add(new Person(emailAddresses[i], " " , 14) );
			// we can drop the name req from the constructor OR get their name for oAuth OR
			// get it from the database
		}
		
		/*
		 * for (int i = 0; i < emailAddresses.length(); ++i) {
		 * //pdao.addPerson(emailAddresses[i], "3R5S");
		 * //System.out.println("first email" + emailAddresses[0]); }
		 */
		
		//this is where we need to output the HTML for logging
		
		// this builds the HTML OBJ table for voting
		String outingObjHTML = "<h2> " + outingName + "</h2>";
		outingObjHTML += "<h4> " + date + "</h4>";
		outingObjHTML += "<form action=\"recordVote\" method =\"get\">" ;
		outingObjHTML += userLoginText;
		// this method builds the voting form we need to tell it the SurveyID
		outingObjHTML += mySurvey.buildVotingeRestaurantTable(surveyID);
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

	@RequestMapping(value ="/voting", method=RequestMethod.GET)
	public ModelAndView recordVoteFromLink(Model model, @RequestParam("voterEmail") String voterEmail, @RequestParam("surveyID") String surveyID) {
		
		
		System.out.println("in beginning of method");
		//we should search the database for the surveyID
		SurveyDaoImpl surveyDB = new SurveyDaoImpl();
		//LINK HAS TO BE FORMATTED WITH NO QUOTES :O 
		SurveyDto surveyDto = surveyDB.searchSurvey(surveyID).get(0);  //this should be filled from the database
		//we build the survey object from the ID
		Survey mySurvey = new Survey(surveyDto);
		
		//TODO get the Outing information: Event Name, Organizer, Date from the outing object, if we are searching by ID by doing a join on the table
		//I tried some SQL queries but we will need help
		
		
		
		String outingObjHTML = "<h2> Thank you " + voterEmail +" </h2> <h3> Please vote below: " + surveyID + "</h3>";
		outingObjHTML = mySurvey.buildVotingeRestaurantTable(surveyID);//when we have the object built we may not need to pass an array 
		//TODO call a method to set the email address
		
		return new ModelAndView("voting", "result", outingObjHTML);
	}
	
	
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

}