package com.gc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gc.api.Credentials;

public class GeolocationAPI {
	 private String typedStreet;
	 private String typedCity;
	 private String typedState;
	 private double latitude;
	 private double longitude;
 
	
	//this fields will come from the form  
	public GeolocationAPI(String typedStreet, String typedCity, String typedState) {
		this.typedStreet = typedStreet;
		this.typedCity = typedCity;
		this.typedState = typedState;
	}
	
	public String getTypedStreet() {
		return typedStreet;
	}

	public void setTypedStreet(String typedStreet) {
		this.typedStreet = typedStreet;
	}

	public String getTypedCity() {
		return typedCity;
	}

	public void setTypedCity(String typedCity) {
		this.typedCity = typedCity;
	}

	public String getTypedState() {
		return typedState;
	}

	public void setTypedState(String typedState) {
		this.typedState = typedState;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	 
	public void calculateLatLong() {
		String formattedAddress= formatAddress();
		//double latitude = 0.0;
		//double longitude = 0.0;

		try {
			// this is how we create the url code in order to call the JSON response
			// with the info we request
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="
					+ formattedAddress + "&key=" + Credentials.GOOGLE_API);

			BufferedReader reader;

			String jsonStr = "";

			// the openstream method allows us to open and read the url that was given in
			// response -- we need to loop through
			reader = new BufferedReader(new InputStreamReader(url.openStream()));

			// can use this or the code snippet after
			String line = reader.readLine();
			//this brings in all the JSON data
			while (line != null) {
				jsonStr += line;
				line = reader.readLine();

			}
			//we use that data to create to objext
			JSONObject jsonObj = new JSONObject(jsonStr);
			
			//this is for debugging
			System.out.println(jsonStr);
			
			JSONArray jsonAr = jsonObj.getJSONArray("results");
			//this is for debugging
			System.out.println(jsonAr);
			
			latitude = jsonAr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			longitude = jsonAr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

			//forPrint += ("<h2>" + latitude + ", " + longitude + "</h2>");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	
	}
	
	public String formatAddress() {
		//to take the street addres and replace the spaces with +
		//place a comma after the end of the street
		//place a comma after the end of the city
		//what happens if they just choose a city and those are null?
		String formattedStreet = typedStreet.replace(' ', '+');
		String formattedCity = typedCity.replace(' ', '+');
		
		return formattedStreet + "," + formattedCity + "," + typedState;
	}
	
}
