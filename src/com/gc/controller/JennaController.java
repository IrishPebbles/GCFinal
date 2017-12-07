package com.gc.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
import com.gc.util.GeolocationAPI;
import com.gc.util.Outing;
import com.gc.util.Person;
import com.gc.util.Survey;

@Controller
public class JennaController {

	@RequestMapping(value= "votingJenna", method = RequestMethod.POST)
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
		
		
		
		//create the table that we need to view based on the voting object
		String outingObjHTML ="<h1> Welcome to the  event !</h1>\n" + 
				"\n" + 
				"<h3> Please vote the restaurants you would like to go, you may choose more than one, if you have a restaurant you have a strong preference for chose just that one.</h3>\n" + 
				"<!--  We need to check some weight math logic. If someone chooses more than one their vote counts for 1/2 or 1/3 of a point, whichever restaurant has  points wins-->\n" + 
				"<h3> </h3>	\n" + 
				"	<form action=\"recordVote\" method =\"post\">\n" + 
				"	<table>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant1\" >" + mySurvey.getPotentialVenues().indexOf(0) +" </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant2\" > " + mySurvey.getPotentialVenues().indexOf(1) + " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant3\" > " + mySurvey.getPotentialVenues().indexOf(2)+ " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant4\" > " + mySurvey.getPotentialVenues().indexOf(3)+ " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	<tr> <!-- I think zumato will send us code --> \n" + 
				"		<td> <input type=\"checkbox\" name=\"restaurant5\" > " +  mySurvey.getPotentialVenues().indexOf(4) + " </td><td> Rating </td>\n" + 
				"	</tr>\n" + 
				"	</table>\n" + 
				"	\n" + 
				"		<input type=\"submit\" value=\"Vote\" > \n" + 
				"	</form>";
		
		
		return new ModelAndView("voting","result", outingObjHTML);
	}
	
	@RequestMapping("/recordVote")
	public ModelAndView recordVote(Model model) {
		
		// get survey object (from Outing object)
		//update the object 
		//let the person know they have voted
		
		return new ModelAndView("voting", "thankYou", "<p> Thank you for voting </p>");
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

	/*// this is a different way to pull json data
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
	} */
}
