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
	private boolean voted;// this needs to be 0/1 (boolean). Unable to initialize as such due to hibernate mapping
	
	public AttendeesDto(){
		
	}
	
	

	public AttendeesDto(int personID, int outingID, int attendeesID, boolean voted) {
		super();
		this.personID = personID;
		this.outingID = outingID;
		this.attendeesID = attendeesID;
		this.voted = voted;
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
	
	

	public boolean getVoted() {
		return voted;
	}



	public void setVoted(boolean voted) {
		this.voted = voted;
	}



	@Override
	public String toString() {
		return "AttendeesDto [personID=" + personID + ", outingID=" + outingID + ", attendeesID=" + attendeesID + "]";
	}
	
}
