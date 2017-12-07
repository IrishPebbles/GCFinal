package com.gc.util;

public class RestaurantObj {

	private String restName;
	private String restLocation;
	private String restRating;

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

	public RestaurantObj() {

	}

	public RestaurantObj(String restName, String restLocation, String restRating) {
		super();
		this.restName = restName;
		this.restLocation = restLocation;
		this.restRating = restRating;
	}

	@Override
	public String toString() {
		return "Restaurant [restName=" + restName + ", restLocation=" + restLocation + ", restRating=" + restRating	+ "]";
	}

}
