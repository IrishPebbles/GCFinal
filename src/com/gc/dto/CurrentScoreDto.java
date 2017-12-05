package com.gc.dto;

import java.io.Serializable;

public class CurrentScoreDto implements  Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private int restaurantID;
	private int totalScore;
	
	public CurrentScoreDto() {
		
	}

	public CurrentScoreDto(int restaurantID, int totalScore) {
		super();
		this.restaurantID = restaurantID;
		this.totalScore = totalScore;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return "currentScoreDto [restaurantID=" + restaurantID + ", totalScore=" + totalScore + "]";
	}
	
	
}
