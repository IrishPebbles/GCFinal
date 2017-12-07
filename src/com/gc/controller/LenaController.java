package com.gc.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;

@Controller
public class LenaController {
	
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

	@RequestMapping(value= "votingLena", method = RequestMethod.POST)
	public ModelAndView voting(@RequestParam("street") String street ,@RequestParam("city") String city,@RequestParam("state") String state, @RequestParam("votingWindow") String votingWindow, Model model) {
		Person organizer = new Person("jenna.otto@gmail.com", "nope", null);
		Person attendee1 = new Person("person1@nothanks.com", "nope", null);
		Person attendee2 = new Person("person1@nothanks.com", "nope", null);
		Person attendee3 = new Person("person1@nothanks.com", "nope", null);
		// constructing a basic outing

		ArrayList<Person> attendees = new ArrayList<>();
		attendees.add(organizer);
		attendees.add(attendee1);
		attendees.add(attendee2);
		attendees.add(attendee3);
		
		
		//create a survey based on the location
		
		//make a call to the api and get the location 
		
		GeolocationAPI location = new GeolocationAPI(street, city, state);
		location.calculateLatLong();
		double locationLat = location.getLatitude();
		double locationLng = location.getLongitude();
		Outing constructingOuting = new Outing(null, null, organizer, attendees);
		
		return new ModelAndView("voting","result", constructingOuting.toString());
	}
}
