package com.gc.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dao.CurrentScoreDao;
import com.gc.dao.CurrentScoreDaoImpl;
import com.gc.dto.CurrentScoreDto;
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.RestaurantObj;
import com.gc.util.Survey;
import com.gc.util.ZoomatoAPI;

@Controller
public class HomeController {
	
	@RequestMapping({"/", "index"})
	public ModelAndView homepage() {
		CurrentScoreDto dto = new CurrentScoreDto(); 
	 CurrentScoreDao dao = new CurrentScoreDaoImpl(); 
		
		dao.addcurrentScore( 1, 2);
		return new ModelAndView("index","", "");
	}
	
	@RequestMapping(value= "voting", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("organizerEmail") String organizerEmail,@RequestParam("emailAddress") String emailAddress, @RequestParam("street") String street ,@RequestParam("city") String city,@RequestParam("state") String state, @RequestParam("votingWindow") String votingWindow, @RequestParam("date") String date, Model model) {
		String[] formatDate = date.split("-");
		Date eventDate = new Date(Integer.parseInt(formatDate[0]), Integer.parseInt(formatDate[1]), Integer.parseInt(formatDate[2]));
		String[] emailAddresses = emailAddress.split(",");
		ArrayList<Person> attendees = new ArrayList<>(emailAddresses.length + 1);//when can from here search the database to see if these people already exist
		
		Person organizer = new Person(organizerEmail, "nope", null);//we may want the organizer's name
		attendees.add(organizer);
		
		for(int i=0; i<emailAddresses.length; i++ ) {
			attendees.add(new Person(emailAddresses[i], null, null));
			//we can drop the name req form the constructor OR get their name for oAuth OR get it from the database
		}
	
		GeolocationAPI location = new GeolocationAPI(street, city, state);
		//passing location to create and return survey
		Outing constructingOuting = new Outing(eventDate, location, organizer, attendees);//date and final location are null
		Survey mySurvey = constructingOuting.getPotentialEvent();
		System.out.println("Info in my survey " + mySurvey.toString());//
		RestaurantObj placeholder = ZoomatoAPI.searchByRestID(mySurvey.getPotentialVenues().get(0));
		System.out.println("Restaurant info " + placeholder.toString());
		
		
		
		//create the table that we need to view based on the voting object
		String outingObjHTML ="<h1> Welcome to the  event !</h1>\n" + 
				"\n" + 
				"<h3> Please vote the restaurants you would like to go, you may choose more than one, if you have a restaurant you have a strong preference for chose just that one.</h3>\n" + 
				"<!--  We need to check some weight math logic. If someone chooses more than one their vote counts for 1/2 or 1/3 of a point, whichever restaurant has  points wins-->\n" + 
				"<h3> </h3>	\n" + 
				"	<form action=\"recordVote\" method =\"post\">\n" + 
				"	<table>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant1\" >" + mySurvey.getPotentialVenues().get(0) +" </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant2\" > " + mySurvey.getPotentialVenues().get(1) + " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant3\" > " + mySurvey.getPotentialVenues().get(2)+ " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant4\" > " + mySurvey.getPotentialVenues().get(3)+ " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant5\" > " +  mySurvey.getPotentialVenues().get(4) + " </td><td> Rating </td>\n" + 
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
