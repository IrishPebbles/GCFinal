package com.gc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gc.api.Credentials;
import com.gc.dao.PersonDaoImpl;
import com.gc.dto.PersonDto;
import com.gc.util.GeolocationAPI;
import com.gc.util.ZoomatoAPI;

@Controller
	
	@SessionAttributes({"authenticated", "username"})
		public class SerhiyController {
		private PersonDaoImpl database = new PersonDaoImpl();
		
		// this is an example showing how to take data from a form and just add it to a page
	
				
		@RequestMapping(value = "/indexlogin", method = RequestMethod.POST)
		public ModelAndView loginCustomer(@RequestParam("userEmail") String username,Model model)throws ClassNotFoundException, SQLException {
		
			ArrayList<PersonDto> user  =  (ArrayList<PersonDto>) database.searchByEmail(username); // we need to enter if statement to count for userEmail not found
			System.out.println(user + "  user is empty "+ user.isEmpty());
			String warning = null;
			if (!user.isEmpty()) {
				model.addAttribute("user",username);
				
				PersonDto searchUser = user.get(0); // getting userEmail from ArrayList<PersonDto> at location zero
				warning =" Welcome " + username;
				
			}
				
			else {
				warning = "<p class='warning'> You do not have an account associated with  "+ username +
						"<form action=\"addnewuserinfo\" method=\"post\">" +
						"Please create an account below: </p> Your user name: <input type=\"email\"name=\"username\"value=\"" + username + "\"><br><br> Please enter your password:     <input type=\"password\"name=\"passwordBox1\"><br> <br> Please Re-enter password your: <input type=\"password\"name=\"passwordBox2\"> <br><br>  <input type=\"submit\"value=\"Submit\"> "; 
				
				//return new ModelAndView("voting", "userName", warning);
				// what we want to do if they don't have account created. 
				
			}	
			
			//System.out.println(warning);
			model.addAttribute("authenticated", username);
			return new ModelAndView("voting", "result", warning);
			
			//return new ModelAndView("preferences", "noAccountMessage", warning);
		}
				
	}
		/*@RequestMapping(value = "/submitform", method = RequestMethod.POST)
		public ModelAndView registerCustomer(@RequestParam("emailaddress") String emailAddress, Model model) throws ClassNotFoundException, SQLException {
			PersonDto user = new PersonDto(emailAddress);
			
			database.insert(user);
			hSession.setAttribute("authenticated", true);
			
			return new ModelAndView("welcome", "customername", customerName);
		}*/
	
	
	/*@RequestMapping("/zomato")
	public ModelAndView index2(Model model) {

		GeolocationAPI myLocation = new GeolocationAPI("1570 Woodward Ave", "Detroit", "MI");
		myLocation.calculateLatLong();

		ZoomatoAPI zAPI = new ZoomatoAPI(myLocation);
		String hmtlDisplay = "";
		for (int i = 0; i < zAPI.getRestID().size(); i++) { // zAPI.getRestID() is an array of Strings
			
			hmtlDisplay += "<h6>" + zAPI.getRestID().get(i) + "</h6>";

		}

		return new ModelAndView("zomato", "restdata", hmtlDisplay);
	}*/
	
	








/*
 * @RequestMapping("/zomato") public ModelAndView index(Model model) { String
 * results = ""; String restarantText = ""; try { // the HttpCLient Interface
 * represents the contract for the HTTP request // execution HttpClient http =
 * HttpClientBuilder.create().build();
 * 
 * // HttpHost holds the variables needed for the connection // default port for
 * http is 80 // default port for https is 443 HttpHost host = new
 * HttpHost("developers.zomato.com", 443 , "https");
 * 
 * // HttpGet retrieves the info identified by the request url (returns as an //
 * entity) HttpGet getPage = new HttpGet(
 * "/api/v2.1/search?entity_id=5&entity_type=city&lat=42.337067&lon=83.052578&radius=20000"
 * ); //TODO need to change parameters later using
 * https://developers.zomato.com/documentation#!/restaurant/search
 * getPage.setHeader("user-key", Credentials.ZOMATO_API); HttpResponse resp =
 * http.execute(host, getPage);
 * 
 * String jsonString = EntityUtils.toString(resp.getEntity()); JSONObject
 * objJson = new JSONObject(jsonString); results =
 * objJson.get("results_shown").toString(); System.out.println(jsonString);
 * System.out.println("Response code: " + resp.getStatusLine().getStatusCode() +
 * " " + results);
 * 
 * 
 * // assign the returned result to a json object
 * 
 * 
 * JSONArray restArray = objJson.getJSONArray("restaurants");// we are creating
 * an array from JSON tree JSONObject restaurant ; for (int i = 0; i <
 * restArray.length(); i++) { //String resultsshown = "";
 * 
 * // restarant = restArray.getJSONObject(i); restarantText += "<h6>" +
 * restArray.getJSONObject(i).getJSONObject("restaurant").getString("name") +
 * "</h6>"; restarantText += "<h6>" +
 * restArray.getJSONObject(i).getJSONObject("restaurant").getJSONObject(
 * "user_rating").getString("aggregate_rating") + "</h6>";
 * 
 * city = json.getJSONObject(i).getString("city"); contact =
 * json.getJSONObject(i).getString("contact");
 * 
 * forPrint += ("<h2>" + center + ", " + city + ", " + contact + "</h2>");
 * 
 * } //prodCenter = String.valueOf(objJson.getInt("results_shown"));
 * 
 * 
 * // the next step is showing how to dig deeper into the json data //String
 * text = ""; // created an array to hold the array for text from json
 * //JSONArray ar = json.getJSONObject("data").getJSONArray("text");
 * 
 * //for (int i = 0; i < ar.length(); i++) { // text += ("<h6>" +
 * ar.getString(i) + "</h6>");
 * 
 * // }
 * 
 * // model.addAttribute("jsonArray", text);
 * 
 * } catch (ClientProtocolException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); } return new ModelAndView("zomato", "restdata",
 * restarantText); } }
 */