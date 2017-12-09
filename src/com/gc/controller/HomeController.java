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
	//Serhiy add @RequestParam("password") String password
	@RequestMapping(value = "voting", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("organizerEmail") String organizerEmail,
			@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street, String eventname,
			@RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("outingName") String eventName, @RequestParam("date") String date, Model model)
/* @RequestParam("votingWindow") String votingWindow, */
			throws ParseException {
		//creating the daoImpl to write to the database
		PersonDao pdao = new PersonDaoImpl();
		OutingDao outDao = new OutingDaoImpl();

		// Changes input java date into sql date
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]),
				Integer.parseInt(formatDate[2]));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		//Adding people coming from the form into relevant databases
		pdao.addPerson(organizerEmail, "7DS8");// we need the id of this organizer for the next push to the database
		//we need to be able to search a person
		outDao.addOuting(eventName, sqlDate, "", 0);
	

		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);// when can from here search the

		for (int i = 0; i < emailAddress.length(); ++i) {
			//pdao.addPerson(emailAddresses[i], "3R5S");
			//System.out.println("first email" + emailAddresses[0]);	
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

			outingObjHTML += "	<tr><td> <input type=\"checkbox\" name=\"rstrnt\" value=\""+placeholder.getRestName() + "\" >"
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
		String outingObjHTML = "<h1> Welcome to the event ! </h1>"
				+ "<h3> Thank you for voting: Here is what was voted</h3>" + "	<table border=\"1\">";

		for (int i = 0; i < restaurantVote.length; i++) {
			outingObjHTML += "	<tr> " + "<td>  " + restaurantVote[i] + "</td> <td> Restaurant </td>" + "	</tr>";
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
		return new ModelAndView("voting", "", "");
	}
}