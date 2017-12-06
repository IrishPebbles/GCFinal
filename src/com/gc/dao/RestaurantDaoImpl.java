/**
 * 
 */
package com.gc.dao;

import java.io.IOException;
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
public class RestaurantDaoImpl implements RestaurantDao {

	@Override
	public List<RestaurantDto> getList(double lat, double longt, double rad) {
		String results = "";
		String restarantText = "";
		List<RestaurantDto> resultsRest = new ArrayList<RestaurantDto>();
		
		try {
			// the HttpCLient Interface represents the contract for the HTTP request
			// execution
			HttpClient http = HttpClientBuilder.create().build();

			// HttpHost holds the variables needed for the connection
			// default port for http is 80
			// default port for https is 443
			HttpHost host = new HttpHost("developers.zomato.com", 443 , "https");

			// HttpGet retrieves the info identified by the request url (returns as an
			// entity)
			HttpGet getPage = new HttpGet("/api/v2.1/search?entity_id=5&entity_type=city&lat=42.337067&lon=83.052578&radius=20000");  //TODO need to change parameters later using https://developers.zomato.com/documentation#!/restaurant/search
			getPage.setHeader("user-key", Credentials.ZOMATO_API);
			HttpResponse resp = http.execute(host, getPage);

			String jsonString = EntityUtils.toString(resp.getEntity());
			JSONObject objJson = new JSONObject(jsonString);
			results = objJson.get("results_shown").toString();
			System.out.println(jsonString);
			System.out.println("Response code: " + resp.getStatusLine().getStatusCode() + " " + results);
			
			
			// assign the returned result to a json object
			
			
			JSONArray restArray = objJson.getJSONArray("restaurants");// we are creating an array from JSON tree
			JSONObject restaurant ;
			for (int i = 0; i < restArray.length(); i++) {
				//String resultsshown = "";
			
			//	 restarant = restArray.getJSONObject(i);
				 restarantText += "<h6>" + restArray.getJSONObject(i).getJSONObject("restaurant").getString("name") + "</h6>";
				 restarantText += "<h6>" + restArray.getJSONObject(i).getJSONObject("restaurant").getJSONObject("user_rating").getString("aggregate_rating") + "</h6>";
				
				/*city = 
				 * json.getJSONObject(i).getString("city");
				contact = json.getJSONObject(i).getString("contact");

				forPrint += ("<h2>" + center + ", " + city + ", " + contact + "</h2>");*/
				
		}
			//prodCenter = String.valueOf(objJson.getInt("results_shown"));

		
			// the next step is showing how to dig deeper into the json data
			//String text = "";
			// created an array to hold the array for text from json
			//JSONArray ar = json.getJSONObject("data").getJSONArray("text");

			//for (int i = 0; i < ar.length(); i++) {
			//	text += ("<h6>" + ar.getString(i) + "</h6>");

		//	}

		//	model.addAttribute("jsonArray", text);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultsRest;
	}
}


