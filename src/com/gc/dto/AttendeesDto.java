package com.gc.dto;

import java.io.Serializable;

public class AttendeesDto implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private int personID;
	private int outingID;
	private int attendeesID;
	
	public AttendeesDto(){
		
	}
	
	

	public AttendeesDto(int personID, int outingID, int attendeesID) {
		super();
		this.personID = personID;
		this.outingID = outingID;
		this.attendeesID = attendeesID;
	}


	public int getAttendeesID() {
		return attendeesID;
	}

	public void setAttendeesID(int attendeesID) {
		this.attendeesID = attendeesID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getOutingID() {
		return outingID;
	}

	public void setOutingID(int outingID) {
		this.outingID = outingID;
	}

	@Override
	public String toString() {
		return "AttendeesDto [personID=" + personID + ", outingID=" + outingID + ", attendeesID=" + attendeesID + "]";
	}
	
}
