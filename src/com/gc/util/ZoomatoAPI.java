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
import com.gc.dto.RestaurantDto;

/**
 * @author Serhiy Bardysh
 *
 */
public class ZoomatoAPI {

	private ArrayList<String> restID;
	private GeolocationAPI location;

	public ZoomatoAPI() {

	}

	public ZoomatoAPI(GeolocationAPI location) {

		this.location = location;
		this.restID = getList();// location has to be set to generate the list of restauran ids

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
		String results = "";
		String restaurantText = "";
		restID = new ArrayList<String>();

		try {
			// the HttpCLient Interface represents the contract for the HTTP request
			// execution
			HttpClient http = HttpClientBuilder.create().build();

			// HttpHost holds the variables needed for the connection
			// default port for http is 80
			// default port for https is 443
			HttpHost host = new HttpHost("developers.zomato.com", 443, "https");

			String radius = "8046.72";
			// HttpGet retrieves the info identified by the request url (returns as an
			// entity)
			HttpGet getPage = new HttpGet("/api/v2.1/search?lat=" + location.getLatitude() + "&lon="
					+ location.getLongitude() + "&radius=" + radius); // TODO need to change parameters later
			// using
			// https://developers.zomato.com/documentation#!/restaurant/search
			getPage.setHeader("user-key", Credentials.ZOMATO_API);
			HttpResponse resp = http.execute(host, getPage);

			String jsonString = EntityUtils.toString(resp.getEntity());
			JSONObject objJson = new JSONObject(jsonString);
			results = objJson.get("results_shown").toString();
			// System.out.println(jsonString);
			System.out.println("Response code: " + resp.getStatusLine().getStatusCode() + " " + results);

			// assign the returned result to a json object
			JSONArray restArray = objJson.getJSONArray("restaurants");// we are creating an array from JSON tree
			JSONObject restaurant;
			for (int i = 0; i < 5; i++) {
				restID.add(i, restArray.getJSONObject(i).getJSONObject("restaurant").getString("id"));

			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restID;

	}

	// this method is minimal stub I have to make call to API with correct
	// parameters. We can also make a method that connects to API for us

	public RestaurantObj searchByRestID(String restID) {
		String restaurantText;

		try {

			HttpClient http = HttpClientBuilder.create().build();

			HttpHost host = new HttpHost("developers.zomato.com", 443, "https");

			HttpGet getPage = new HttpGet("/api/v2.1/search?name=" + name + ); // TODO need to change parameters later
			// using
			// https://developers.zomato.com/documentation#!/restaurant/search
			getPage.setHeader("user-key", Credentials.ZOMATO_API);
			HttpResponse resp = http.execute(host, getPage);

			String jsonString = EntityUtils.toString(resp.getEntity());
			JSONObject objJson = new JSONObject(jsonString);
			String results = objJson.get("results_shown").toString();

			JSONArray restArray = objJson.getJSONArray("restaurants");// we are creating an array from JSON tree

			JSONObject restaurant;
			for (int i = 0; i < restArray.length(); i++) {
				// String resultsshown = "";

				// Restaurant = restArray.getJSONObject(i);
				restaurantText += "<h6>" + restArray.getJSONObject(i).getJSONObject("restaurant").getString("name")
						+ "</h6>";
				restaurantText += "<h6>" + restArray.getJSONObject(i).getJSONObject("restaurant").getString("cuisines")
						+ "</h6>";
				restaurantText += "<h6>" + restArray.getJSONObject(i).getJSONObject("restaurant")
						.getJSONObject("user_rating").getString("aggregate_rating") + "</h6>";

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
