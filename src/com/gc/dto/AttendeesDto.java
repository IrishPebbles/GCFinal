package com.gc.dto;

import java.io.Serializable;

public class AttendeesDto implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private int personID;
	private int outingID;
	
	AttendeesDto(){
		
	}

	public AttendeesDto(int personID, int outingID) {
		super();
		this.personID = personID;
		this.outingID = outingID;
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
		return "attendeesDto [personID=" + personID + ", outingID=" + outingID + "]";
	}
	
}
