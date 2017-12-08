package com.gc.dto;

import java.io.Serializable;

public class CurrentScoreDto implements  Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private int restaurantID;
	private int totalScore;
	private int currentScoreID;
	
	public CurrentScoreDto() {
		
	}
	

	public CurrentScoreDto(int restaurantID, int totalScore, int CurrentScoreID) {
		super();
		this.restaurantID = restaurantID;
		this.totalScore = totalScore;
		this.currentScoreID = currentScoreID;
	}

	public int getCurrentScoreID() {
		return currentScoreID;
	}

	public void setCurrentScoreID(int currentScoreID) {
		this.currentScoreID = currentScoreID;
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
		return "CurrentScoreDto [restaurantID=" + restaurantID + ", totalScore=" + totalScore + ", currentScoreID="
				+ currentScoreID + "]";
	}
	
	
}
