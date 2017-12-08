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

@Controller
public class HomeController {
	
	@RequestMapping({"/", "index"})
	public ModelAndView homepage() {
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
	
	//This is a test method to show data entry is working
	@RequestMapping(value="voting", method = RequestMethod.POST)
	public ModelAndView addToSql(@RequestParam("organizerEmail") String orgEmail) {
		PersonDao dao = new PersonDaoImpl();
		dao.addPerson(orgEmail, "7564");
		return new ModelAndView("voting", "", "");
	}
	
	
	
	//commented out for functionallity
	@RequestMapping(value= "voting", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("organizerEmail") String organizerEmail,@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street ,@RequestParam("city") String city,@RequestParam("state") String state, @RequestParam("votingWindow") String votingWindow, @RequestParam("date") String date, Model model) {
		Date eventDate = new Date("date");
		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);//when can from here search the database to see if these people already exist
		
		Person organizer = new Person(organizerEmail, "nope", null);//we may want the organizer's name
		attendees.add(organizer);
		
		for(int i=0; i< emailAddresses.length; i++ ) {
			attendees.add(new Person(emailAddresses[i], null, null));
			//we can drop the name req form the constructor OR get their name for oauth OR get it from the database
		}
	
		GeolocationAPI location = new GeolocationAPI(street, city, state);
		Outing constructingOuting = new Outing(eventDate, location, organizer, attendees);//date and final location are null
		
		
		//create the table that we need to view based on the voting object
		String outingObjHTML ="<h1> Welcome to the  event !</h1>\n" + 
				"\n" + 
				"<h3> Please vote the restaurants you would like to go, you may choose more than one, if you have a restaurant you have a strong preference for chose just that one.</h3>\n" + 
				"<!--  We need to check some weight math logic. If someone chooses more than one their vote counts for 1/2 or 1/3 of a point, whichever restaurant has  points wins-->\n" + 
				"<h3> </h3>	\n" + 
				"	<form action=\"recordVote\" method =\"post\">\n" + 
				"	<table>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant1\" >Restaurant 1 </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant2\" > Restaurant 2 </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant3\" > Restaurant 3 </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant4\" > Restaurant 4 </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant5\" > Restaurant 5 </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	</table>\n" + 
				"	\n" + 
				"		<input type=\"submit\" value=\"Vote\" > \n" + 
				"	</form>";
		
		
		return new ModelAndView("voting","result", outingObjHTML);
	}
	
	@RequestMapping("/geolocation")
	public ModelAndView latitudeAndLongitude(Model model) {
		String forPrint = "";
		
		String myStreet = "";
		String myCity ="";
		String myState = "";
		
		GeolocationAPI coordinates = new GeolocationAPI(myStreet, myCity, myState);
		coordinates.calculateLatLong();
		forPrint = coordinates.getLatitude() + " , " + coordinates.getLongitude();
		
		return new ModelAndView("geolocation","latLongData",forPrint);
	}

	@RequestMapping("/recordVote")
	public ModelAndView recordVote(Model model) {
		
		// get survey object (from Outing object)
		//update the object 
		//let the person know they have voted
		
		return new ModelAndView("voting", "thankYou", "<p> Thank you for voting </p>");
	}

	@RequestMapping("preferences")
	public ModelAndView preferences() {
		return new ModelAndView("preferences","", "");
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
