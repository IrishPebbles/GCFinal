package com.gc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gc.util.GeolocationAPI;

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

}
