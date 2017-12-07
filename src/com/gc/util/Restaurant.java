package com.gc.util;

public class Restaurant {
	
	private String restID;// this comes from Zumato
	private String restName;
	private String restLocation;
	private String restRating;

	public Restaurant() {
		
	}
	public Restaurant(String restID) {
		this.restID = restID;
	}
	
	public Restaurant(String restName, String restLocation, String restRating) {
		super();
		this.restName = restName;
		this.restLocation = restLocation;
		this.restRating = restRating;
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
	public String getRestID() {
		return restID;
	}
	public void setRestID(String restID) {
		this.restID = restID;
	}
	@Override
	public String toString() {
		return "Restaurant [restID=" + restID + ", restName=" + restName + ", restLocation=" + restLocation
				+ ", restRating=" + restRating + "]";
	}
	

}



