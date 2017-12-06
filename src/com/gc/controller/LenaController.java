package com.gc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gc.api.Credentials;
import com.gc.util.GeolocationAPI;

@Controller
public class LenaController {
	@RequestMapping("/geolocation")
	public ModelAndView latitudeAndLongitude(Model model) {
		double latitude = 0.0;
		double longitude = 0.0;
		String forPrint = "";
		
		String myStreet ="1117 Burgoyne Blvd";
		String myCity ="Rochester Hills";
		String myState = "MI";
		
		GeolocationAPI coordinates = new GeolocationAPI(myStreet, myCity, myState);
		coordinates.calculateLatLong();
		forPrint = coordinates.getLatitude() + " , " + coordinates.getLongitude();
		/*
		try {
			// this is how we create the url code in order to call the JSON response
			// with the info we request
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="
					+ "1600+Amphitheatre+Parkway,+Mountain+View,+CA" + "&key=" + Credentials.GOOGLE_API);

			BufferedReader reader;

			String jsonStr = "";

			// the openstream method allows us to open and read the url that was given in
			// response -- we need to loop through
			reader = new BufferedReader(new InputStreamReader(url.openStream()));

			// can use this or the code snippet after
			String line = reader.readLine();

			while (line != null) {
				jsonStr += line;
				line = reader.readLine();

			}

			JSONObject jsonObj = new JSONObject(jsonStr);
			
			System.out.println(jsonStr);
			JSONArray jsonAr = jsonObj.getJSONArray("results");
			
			System.out.println(jsonAr);
			latitude = jsonAr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			longitude = jsonAr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble
					("lng");

			forPrint += ("<h2>" + latitude + ", " + longitude + "</h2>");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return new ModelAndView("geolocation","latLongData",forPrint);
	}

}
