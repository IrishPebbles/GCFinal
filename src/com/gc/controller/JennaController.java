package com.gc.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.api.Credentials;
import com.gc.dao.SurveyDao;
import com.gc.dao.SurveyDaoImpl;
import com.gc.dto.SurveyDto;
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.RestaurantObj;
import com.gc.util.Survey;

@Controller
public class JennaController {
	@RequestMapping(value = "preferencesJ", method = RequestMethod.POST)
	public ModelAndView viewifLoggedIn(Model model, @RequestParam("userEmail") String userEmail) {
		
		model.addAttribute("user", "loggedin");
		model.addAttribute("displayPreference", "\"display:block;\"");
		model.addAttribute("displayPreference", "\"display:block;\"");
		model.addAttribute("shown", "show");
		model.addAttribute("username", userEmail);
	

		return new ModelAndView("index", "", "");
	}

	@RequestMapping("/eventbrite")
	public ModelAndView eventbriteAPI(Model model) {
		String text = "";
		try {
			// the HttpCLient Interface represents the contract for the HTTP request
			// execution
			HttpClient http = HttpClientBuilder.create().build();

			// HttpHost holds the variables needed for the connection
			// default port for http is 80
			// default port for https is 443
			HttpHost host = new HttpHost("www.eventbriteapi.com", 443, "https");

			// HttpGet retrieves the info identified by the request url (returns as an
			// entity)
			//lat=42.331427&lon=-83.045754
			//
			HttpGet getPage = new HttpGet("/v3/events/search/?sort_by=best&location.latitude=42.331427&location.longitude=-83.045754&token="+ Credentials.EVENTBRITE_PERSONAL_TOKEN);
			// ***** you may need the following code snippets to work with APIs that require HttpPost or parameters like an api key

			HttpResponse resp = http.execute(host, getPage);

			String jsonString = EntityUtils.toString(resp.getEntity());

			// assign the returned result to a json object
			JSONObject json = new JSONObject(jsonString);

			System.out.println("Response code: " + resp.getStatusLine().getStatusCode());

			// the next step is showing how to dig deeper into the json data
			// created an array to hold the array for text from json
			System.out.println("JSON String " + json);
			//JSONObject objforArray = json.getJSONObject("array");
			JSONArray ar = json.getJSONArray("events");

			for (int i = 0; i < ar.length(); i++) {
				text += ("<h6>" + ar.getJSONObject(i).getJSONObject("name").getString("text") + "</h6>");

			}

			//model.addAttribute("jsonArray", text);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("eventbrite", "data", text);
	}
	
	@RequestMapping(value="resultsPage", method=RequestMethod.GET)
	public ModelAndView showFinalResults(@RequestParam("surveyid") String surveyID) { //what parameters
		//get the survey page and build a survey obj-- really we should get an outing objects
		Survey surveyObj = new Survey(surveyID);
		SurveyDaoImpl surveyConnection = new SurveyDaoImpl();
		List<SurveyDto> results = surveyConnection.searchSurvey(surveyObj.getSurveyID());
		SurveyDto shown = results.get(0);
		ArrayList<String> potentialVenues =surveyObj.getPotentialVenues();
		potentialVenues.add(shown.getOptVenueID1());
		potentialVenues.add(shown.getOptVenueID2());
		potentialVenues.add(shown.getOptVenueID3());
		potentialVenues.add(shown.getOptVenueID4());
		
		return new ModelAndView("voting", "result", "");
	}
	

	// this is a different way to pull json data
	@RequestMapping("/nasajson")
	public String nasaJson(Model model) {
		String center = "";
		String city = "";
		String contact = "";
		String forPrint = "";

		try {
			// this is how we create the url code in order to call the JSON response
			// with the info we request
			URL url = new URL("https://data.nasa.gov/resource/9g7e-7hzz.json");

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

			JSONArray json = new JSONArray(jsonStr);
			// this is where we start parsing the json data so I will loop through the array
			for (int i = 0; i < json.length(); i++) {
				center = json.getJSONObject(i).getString("center");
				city = json.getJSONObject(i).getString("city");
				contact = json.getJSONObject(i).getString("contact");

				forPrint += ("<h2>" + center + ", " + city + ", " + contact + "</h2>");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("nasaData", forPrint);
		return "nasa";
	} 
}
