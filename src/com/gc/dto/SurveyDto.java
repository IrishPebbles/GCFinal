package com.gc.dto;

import java.io.Serializable;

public class SurveyDto  implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private int surveyID;
	private int restaurantID;
	private boolean hasVoted;
	
	SurveyDto(){
		
	}

	public SurveyDto(int surveyID, int restaurantID, boolean hasVoted) {
		super();
		this.surveyID = surveyID;
		this.restaurantID = restaurantID;
		this.hasVoted = hasVoted;
	}

	public int getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public boolean isHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	@Override
	public String toString() {
		return "SurveyDto [surveyID=" + surveyID + ", restaurantID=" + restaurantID + ", hasVoted=" + hasVoted + "]";
	}
	
}
