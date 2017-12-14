package com.gc.util;

public class RestaurantObj {

	private String zomatoID;
	private String restName;
	private String restLocation;
	private String restRating;
	private String restURL;
	private 	String featImgURL;
	private String cusineType;
	
	public String getRestURL() {
		return restURL;
	}

	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}

	public RestaurantObj() {

	}

	public RestaurantObj(String restName, String restLocation, String restRating, String zomatoID) {
		super();
		this.restName = restName;
		this.restLocation = restLocation;
		this.restRating = restRating;
		this.zomatoID = zomatoID;
	}
	public RestaurantObj(String zomatoID) {
		this.zomatoID = zomatoID;
		
	}
	

	public String getZomatoID() {
		return zomatoID;
	}

	public void setZomatoID(String zomatoID) {
		this.zomatoID = zomatoID;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getRestLocation() {
		return restLocation;
	}

	public void setRestLocation(String restLocation) {
		this.restLocation = restLocation;
	}

	public String getRestRating() {
		return restRating;
	}

	public void setRestRating(String restRating) {
		this.restRating = restRating;
	}

	public String getFeatImgURL() {
		return featImgURL;
	}

	public void setFeatImgURL(String featImgURL) {
		this.featImgURL = featImgURL;
	}

	public String getCusineType() {
		return cusineType;
	}

	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}

	@Override
	public String toString() {
		return "RestaurantObj [zomatoID=" + zomatoID + ", restName=" + restName + ", restLocation=" + restLocation
				+ ", restRating=" + restRating + ", restURL=" + restURL + ", featImgURL=" + featImgURL + ", cusineType="
				+ cusineType + "]";
	}

	
	

}
