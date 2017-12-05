package com.gc.dto;

import java.io.Serializable;

public class RestaurantDto implements  Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int restaurantID;
	private String restaurantLocation;
	private double restaurantRating;
	
	public RestaurantDto() {
		
	}

	public RestaurantDto(int restaurantID, String restaurantLocation, double restaurantRating) {
		super();
		this.restaurantID = restaurantID;
		this.restaurantLocation = restaurantLocation;
		this.restaurantRating = restaurantRating;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getRestaurantLocation() {
		return restaurantLocation;
	}

	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}

	public double getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(double restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	@Override
	public String toString() {
		return "RestarantDto [restaurantID=" + restaurantID + ", restaurantLocation=" + restaurantLocation
				+ ", restaurantRating=" + restaurantRating + "]";
	}
	
	
}
