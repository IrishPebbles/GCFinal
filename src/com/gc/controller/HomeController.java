package com.gc.controller;

import java.util.ArrayList;
import java.util.Date;

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
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.RestaurantObj;
import com.gc.util.Survey;
import com.gc.util.ZoomatoAPI;

@Controller
public class HomeController {

	@RequestMapping({ "/", "index" })
	public ModelAndView homepage() {

<<<<<<< HEAD
		CurrentScoreDto dto = new CurrentScoreDto(); 
	 CurrentScoreDao dao = new CurrentScoreDaoImpl(); 
	 AttendeesDao adao = new AttendeesDaoImpl();
	 OutingDao odao = new OutingDaoImpl();
	 PersonDao pdao = new PersonDaoImpl(); 
	 SurveyDao sdao = new SurveyDaoImpl();
	
	 
	 //Below is code to turn a Java simple date variable into a sql-compatible variable.
	 //TODO: Turn into method to be implemented upon data input.
	/* DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = "2018-07-23";
		java.util.Date myDate;
		try {
			myDate = formatter.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			odao.addOuting("Red Robins", sqlDate, "Red Robins", 5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//dao.addcurrentScore( 1, 2);
		//adao.addNewID(3, 4);
	 	//odao.addOuting("Red Robins", sqlDate, "Red Robins", 5);
	// pdao.addPerson("wakkawakkaF0zzY345@yahoo.com", "6Y0N");
	 	System.out.println(odao.getOutingID(1));
		return new ModelAndView("index","", "");
		
	}
	
/*	//This is a test method to show data entry is working
	@RequestMapping(value="voting", method = RequestMethod.POST)
	public ModelAndView addToSql(@RequestParam("organizerEmail") String orgEmail) {
		PersonDao dao = new PersonDaoImpl();
		dao.addPerson(orgEmail, "7564");
		return new ModelAndView("voting", "", "");
	}*/
	
	
/*	
	//commented out for functionallity
	@RequestMapping(value= "voting", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("organizerEmail") String organizerEmail,@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street ,@RequestParam("city") String city,@RequestParam("state") String state, @RequestParam("votingWindow") String votingWindow, @RequestParam("date") String date, Model model) {

=======
>>>>>>> 88520c358a73df2ff191b713c2486f0ae721e7fe
		CurrentScoreDto dto = new CurrentScoreDto();
		CurrentScoreDao dao = new CurrentScoreDaoImpl();
		AttendeesDao adao = new AttendeesDaoImpl();
		OutingDao odao = new OutingDaoImpl();
		PersonDao pdao = new PersonDaoImpl();
		SurveyDao sdao = new SurveyDaoImpl();

		return new ModelAndView("index", "", "");*/

	


	@RequestMapping(value = "voting", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("organizerEmail") String organizerEmail,
			@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("votingWindow") String votingWindow, @RequestParam("date") String date, Model model) {
<<<<<<< HEAD

=======
>>>>>>> 88520c358a73df2ff191b713c2486f0ae721e7fe
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]),
				Integer.parseInt(formatDate[2]));
		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the
																					// database to see if these people
																					// already exist

		Person organizer = new Person(organizerEmail, "nope", null);// we may want the organizer's name
		attendees.add(organizer);

		for (int i = 0; i < emailAddresses.length; i++) {
			attendees.add(new Person(emailAddresses[i], null, null));
			// we can drop the name req form the constructor OR get their name for oAuth OR
			// get it from the database
		}

		GeolocationAPI location = new GeolocationAPI(street, city, state);
		// passing location to create and return survey
		Outing constructingOuting = new Outing(eventDate, location, organizer, attendees);// date and final location are
																							// null
		Survey mySurvey = constructingOuting.getPotentialEvent();
		System.out.println("Info in my survey " + mySurvey.toString());//
		RestaurantObj placeholder = ZoomatoAPI.searchByRestID(mySurvey.getPotentialVenues().get(0));
		System.out.println("Restaurant info " + placeholder.toString());

		// create the table that we need to view based on the voting object
		String outingObjHTML = "<h1> Welcome to the event ! </h1>"
				+ "<h3> Please vote below</h3>"
				+ "<h5>You may vote for more than one choice. Each vote will be weighted equally</h5>"
				+ "	<form action=\"recordVote\" method =\"post\">" + "	<table border=\"1\">";
		
		for (int i = 0; i < 5; i++) {
			placeholder = ZoomatoAPI.searchByRestID(mySurvey.getPotentialVenues().get(i));
			outingObjHTML += "	<tr> " + "<td> <input type=\"checkbox\" name=\"restaurant1\" >"
					+ placeholder.getRestName() + "</td><td> Rating:" 
					+ placeholder.getRestRating() + "</td>\n"
					+ "	</tr>";
		}
		
		outingObjHTML += "</table> " + "<input type=\"submit\" value=\"Vote\" > </form>";

		return new ModelAndView("voting", "result", outingObjHTML);
	}

	@RequestMapping("/recordVote")
	public ModelAndView recordVote(Model model) {
		// we have to know who voter is
		String userEmail = "jenna.otto@gmail.com";
		Survey surveyInstance = new Survey();

		// get survey object (from Outing object)
		// update the object
		// let the person know they have voted

		return new ModelAndView("voting", "thankYou", "<p> Thank you for voting </p>");
	}

<<<<<<< HEAD

	@RequestMapping("preferences")
	public ModelAndView preferences() {
		
		
		
		
		return new ModelAndView("voting","", "");
	}
	
	@RequestMapping("voting")
	public ModelAndView voting() {
		return new ModelAndView("voting","", "");
	}
	
	@RequestMapping("finalResults")
	public ModelAndView finalResult() {
		return new ModelAndView("finalResults","", "");
	}

}

=======
}
>>>>>>> 88520c358a73df2ff191b713c2486f0ae721e7fe
