package com.gc.dto;

import java.sql.Date;

public class OutingDto {
	
	private int outingID;
	private String outingName;
	private Date dateOfEvent;
	private String finalLocation;
	private String organizer;
	
	OutingDto(){
		
	}

	public OutingDto(int outingID, String outingName, Date dateOfEvent, String finalLocation, String organizer) {
		super();
		this.outingID = outingID;
		this.outingName = outingName;
		this.dateOfEvent = dateOfEvent;
		this.finalLocation = finalLocation;
		this.organizer = organizer;
	}

	public int getOutingID() {
		return outingID;
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

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	@Override
	public String toString() {
		return "OutingDto [outingID=" + outingID + ", outingName=" + outingName + ", dateOfEvent=" + dateOfEvent
				+ ", finalLocation=" + finalLocation + ", organizer=" + organizer + "]";
	}
	
	
}
