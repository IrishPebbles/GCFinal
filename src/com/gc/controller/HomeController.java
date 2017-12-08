package com.gc.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		CurrentScoreDto dto = new CurrentScoreDto();
		CurrentScoreDao dao = new CurrentScoreDaoImpl();
		AttendeesDao adao = new AttendeesDaoImpl();
		OutingDao odao = new OutingDaoImpl();
		PersonDao pdao = new PersonDaoImpl();
		SurveyDao sdao = new SurveyDaoImpl();

		return new ModelAndView("index", "result", "");

	}
	
	@RequestMapping(value = "voting", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("organizerEmail") String organizerEmail,
			@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street,
			@RequestParam("city") String city, @RequestParam("state") String state,

			/*@RequestParam("votingWindow") String votingWindow,*/ @RequestParam("date") String date, Model model) throws ParseException {

		PersonDao pdao = new PersonDaoImpl();
		OutingDao outDao = new OutingDaoImpl();
		
		

		
		//Changes input java date into sql date
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]),
				Integer.parseInt(formatDate[2]));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		
		
		//Adding people coming from gthe form into relevant databases
		pdao.addPerson(organizerEmail, "7DS8");
		outDao.addOuting("Fun Times", sqlDate, "", 5); 
			
		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the
																					// database to see if these people
		//System.out.println("first email" + (attendees.get(0).getUserEmail()));											// already exist

		for (int i = 0; i < attendees.size(); ++i) {
			pdao.addPerson(attendees.get(i).getUserEmail().toString(), "3R5S");
			System.out.println(attendees.get(i).getUserEmail().toString());
		}
		
		
		Person organizer = new Person(organizerEmail, "nope", null);// we may want the organizer's name
		attendees.add(organizer);

		for (int i = 0; i < emailAddresses.length; i++) {
			attendees.add(new Person(emailAddresses[i], null, null));
			// we can drop the name req form the constructor OR get their name for oAuth OR
			// get it from the database
		}

		GeolocationAPI location = new GeolocationAPI(street, city, state);
		// passing location to create and return survey
		Outing constructingOuting = new Outing(sqlDate, location, organizer, attendees);// date and final location are
																							// null

		Survey mySurvey = constructingOuting.getPotentialEvent();
		System.out.println("Info in my survey " + mySurvey.toString());//
		RestaurantObj placeholder = ZoomatoAPI.searchByRestID(mySurvey.getPotentialVenues().get(0));
		System.out.println("Restaurant info " + placeholder.toString());

		// create the table that we need to view based on the voting object
		String outingObjHTML = "<h1> Welcome to the event ! </h1>" + "<h3> Please vote below</h3>"
				+ "<h5>You may vote for more than one choice. Each vote will be weighted equally</h5>"
				+ "	<form action=\"recordVote\" method =\"get\">" + "	<table border=\"1\">";

		for (int i = 0; i < 5; i++) {
			placeholder = ZoomatoAPI.searchByRestID(mySurvey.getPotentialVenues().get(i));
			outingObjHTML += "	<tr><td> <input type=\"checkbox\" name=\"rstrnt\" value=\"restaurant" + i + "\" >"
					+ placeholder.getRestName() + "</td><td> Rating:" + placeholder.getRestRating() 
					+ "</td>\n</tr>";
		}

		outingObjHTML += "</table> " + "<input type=\"submit\" value=\"Vote\" > </form>";

		return new ModelAndView("voting", "result", outingObjHTML);
	}

	@RequestMapping("/recordVote")
	public ModelAndView recordVote(Model model, @RequestParam("rstrnt") String[] restaurantVote) {
		System.out.println(restaurantVote.toString());
		
		// we have to know who voter is
		String userEmail = "jenna.otto@gmail.com";
		// System.out.println(restaurantVotes);
//		String[] votes = { "restaurant0", "restaurant1", "restaurant2", "restaurant3", "restaurant4" };

		String outingObjHTML = "<h1> Welcome to the event ! </h1>"
				+ "<h3> Thank you for voting: Here is what was voted</h3>" + "	<table border=\"1\">";

		for (int i = 0; i < restaurantVote.length; i++) {
			outingObjHTML += "	<tr> " + "<td>  " + restaurantVote[i] + "</td> <td> Restaurant " + i + "</td>" + "	</tr>";
		}
		outingObjHTML += "</table> ";
		// get survey object (from Outing object)
		// update the object
		// let the person know they have voted

		return new ModelAndView("voting", "thankYou", outingObjHTML);
	}

	
 	
 	@RequestMapping("preferences")
 	public String viewPreferencesPage() {
 		System.out.println("Here");
 		
 		return "preferences";
 	}
 	

 	@RequestMapping("voting")
 	public ModelAndView voting() {
 		return new ModelAndView("voting","", "");
 	}
}