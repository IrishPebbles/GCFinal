/**
 * 
 */
package com.gc.util;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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

import com.gc.api.Credentials;
import com.sun.jersey.json.impl.writer.JsonEncoder;

/**
 * @author Serhiy Bardysh
 *
 */
public class ZoomatoAPI {

	private ArrayList<String> restID;
	private GeolocationAPI location;
	private ArrayList<String> restURL;

	public ZoomatoAPI() {

	}

	public ZoomatoAPI(GeolocationAPI location) {

		this.location = location;
		this.restID = getList();// location has to be set to generate the list of restaurant ids

	}

	public ArrayList<String> getRestID() {
		return restID;
	}

	public void setRestID(ArrayList<String> restID) {
		this.restID = restID;
	}

	public GeolocationAPI getLocation() {
		return location;
	}

	public void setLocation(GeolocationAPI location) {
		this.location = location;
	}

	public ArrayList<String> getList() {
		restID = new ArrayList<String>();
		JSONObject objJson =connectToAPI(buildParameterforList("8046.72"));//this number is a radius of 5 miles in meters
		JSONObject restaurant = null;
		
		// assign the returned result to a json object
		JSONArray restArray = objJson.getJSONArray("restaurants");// we are creating an array from JSON tree
		for (int i = 0; i < 5; i++) {
			
			restaurant = restArray.getJSONObject(i).getJSONObject("restaurant");
			restID.add(i, restaurant.getString("id"));

		}
		
		return restID;
	}
	
	public ArrayList<String> getNewList() {
		restID = new ArrayList<String>();
		JSONObject objJson =connectToAPI(buildParameterforList("2046.72"));//this number is a radius of 5 miles in meters
		JSONObject restaurant = null;
		
		// assign the returned result to a json object
		JSONArray restArray = objJson.getJSONArray("restaurants");// we are creating an array from JSON tree
		for (int i = 5; i < 10; i++) {
			
			restaurant = restArray.getJSONObject(i).getJSONObject("restaurant");
			restID.add(i, restaurant.getString("id"));

		}
		
		return restID;
	}

	// this method is minimal stub I have to make call to API with correct
	// parameters. We can also make a method that connects to API for us

	public static RestaurantObj searchByRestID(String restID) {
		JSONObject restaurant = connectToAPI(buildParameterforSearch(restID));
		System.out.println(" Search " + restID);
		String restName = restaurant.getString("name");
		String restLocation = restaurant.getJSONObject("location").getString("address");
		String restRating = restaurant.getJSONObject("user_rating").getString("aggregate_rating");
		String restCuisine = restaurant.getString("cuisines");//this may have to be parsed if there are more than one type
		String restURL = restaurant.getString("url");
		String featImage = restaurant.getString("featured_image");
			
		RestaurantObj myRest = new RestaurantObj(restName, restLocation, restRating, restID);
		myRest.setRestURL(restURL);
		myRest.setCusineType(restCuisine);
		myRest.setFeatImgURL(featImage);
		return myRest;
	}
	
	
	public static JSONObject connectToAPI(String parameter) {
		String results = "";
		JSONObject objJson =null;
		try {
			// the HttpCLient Interface represents the contract for the HTTP request
			// execution
			HttpClient http = HttpClientBuilder.create().build();

			// HttpHost holds the variables needed for the connection
			// default port for http is 80
			// default port for https is 443
			HttpHost host = new HttpHost("developers.zomato.com", 443, "https");

			
			// HttpGet retrieves the info identified by the request url (returns as an entity)
			HttpGet getPage = new HttpGet("/api/v2.1/" + parameter);
			// using https://developers.zomato.com/documentation#!/restaurant/search
			getPage.setHeader("user-key", Credentials.ZOMATO_API);
			HttpResponse resp = http.execute(host, getPage);

			String jsonString = EntityUtils.toString(resp.getEntity());
			objJson = new JSONObject(jsonString);
	
			}
			catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			return objJson;

	}
		
	public String buildParameterforList(String radius) {
		String buildParam ="search?lat=" + location.getLatitude() + "&lon="+ location.getLongitude() + "&radius=" + radius;
		//System.out.println("https://developers.zomato.com/api/v2.1/"+ buildParam);
		return buildParam; // TODO need to change parameters later
	}
	
	public static String buildParameterforSearch(String restaurantID) {
		return "restaurant?res_id=" + restaurantID; // TODO need to change parameters later
	}
	
	public static String getStyling(RestaurantObj restaurant) {
		//System.out.println("Url= " + restaurant.getFeatImgURL());
		String htmlCard = "<div class=\"card\"><a href=\"" + restaurant.getRestURL()+"\" target=\"_blank\" >" + 
				"  <img class=\"card-img-top\" src=\"\" alt=\"\">\n" + 
				"  <div class=\"card-block\">\n" + 
				"    <p class=\"card-text\">" + restaurant.getRestName() + " Cusine Type:" + restaurant.getCusineType()+" with a rating of " + restaurant.getRestRating()+
				"  <br> Located at "+ restaurant.getRestLocation() + "  </p>" + "  </div>\n" + 
				"</a></div>";
		
		return htmlCard;
		
	}

}
