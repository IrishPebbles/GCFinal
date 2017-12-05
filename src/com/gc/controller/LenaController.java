package com.gc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gc.api.Credentials;

@Controller
public class LenaController {
	@RequestMapping("/geolocation")
	public ModelAndView latitudeAndLongitude(Model model) {
		String latitude = "";
		String longitude = "";
		String forPrint = "";

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
				System.out.println(line);
				jsonStr += line;
				line = reader.readLine();

			}
			System.out.println(jsonStr);
			

			JSONArray json = new JSONArray(jsonStr);
			latitude = json.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat");
			longitude = json.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lng");

			forPrint += ("<h2>" + latitude + ", " + longitude + "</h2>");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("geolocation","latLongData",forPrint);
	}

}
