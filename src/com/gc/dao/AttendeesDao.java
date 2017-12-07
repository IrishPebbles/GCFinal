package com.gc.dao;

import java.util.List;

import com.gc.dto.AttendeesDto;

public interface AttendeesDao {
	public void getUserID(AttendeesDto attenID);
	
	
	List<AttendeesDto> addNewID (int userID, int outingID );
	// adding new ID
	
	List<AttendeesDto> searchID (AttendeesDto newUser);
	// searching ID
	
	List<AttendeesDto> unpdateID (AttendeesDto newUser);
	// updating ID in case Attendees will become organizer
	
	List<AttendeesDto> getID (AttendeesDto newUser);
	
	
}
