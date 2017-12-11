package com.gc.dto;

import java.io.Serializable;
import java.util.Date; 

public class OutingDto implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private int outingID;
	private String surveyID;
	private String outingName;
	private Date dateOfEvent;
	private String finalLocation;
	private int organizer;//this is the id of the organizer
	
	
	public OutingDto(){
		
	}

	public OutingDto(int outingID, String surveyID, String outingName, Date dateOfEvent, String finalLocation, int organizer) {
		super();
		this.outingID = outingID;
		this.surveyID = surveyID;
		this.outingName = outingName;
		this.dateOfEvent = dateOfEvent;
		this.finalLocation = finalLocation;
		this.organizer = organizer;
	}

	public int getOutingID() {
		return outingID;
	}
	
	public String getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(String surveyID) {
		this.surveyID = surveyID;
	}

	public void setOutingID(int outingID) {
		this.outingID = outingID;
	}

	public String getOutingName() {
		return outingName;
	}

	public void setOutingName(String outingName) {
		this.outingName = outingName;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String getFinalLocation() {
		return finalLocation;
	}

	public void setFinalLocation(String finalLocation) {
		this.finalLocation = finalLocation;
	}

	public int getOrganizer() {
		return organizer;
	}

	public void setOrganizer(int organizer) {
		this.organizer = organizer;
	}

	@Override
	public String toString() {
		return "OutingDto [outingID=" + outingID + ", surveyID=" + surveyID + ", outingName=" + outingName
				+ ", dateOfEvent=" + dateOfEvent + ", finalLocation=" + finalLocation + ", organizer=" + organizer
				+ "]";
	}
		
}
