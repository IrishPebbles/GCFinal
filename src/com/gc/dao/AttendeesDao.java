package com.gc.dao;

import java.util.List;

import com.gc.dto.AttendeesDto;

public interface AttendeesDao {
	public void getUserID(AttendeesDto attenID);
	
	
	List<AttendeesDto> addNewAttendees (int userID, int outingID );
	// adding new ID
	
	List<AttendeesDto> searchID (int attendeesID);
	// searching ID
	
	List<AttendeesDto> updateAttendees (AttendeesDto newUser);
	// updating ID in case Attendees will become organizer
	
	List<AttendeesDto> getID (AttendeesDto newUser);
	
	List<AttendeesDto> searchByPersonID (int personID);
	
	List<AttendeesDto> searchByOutingID (int outingID); 
	
	
}
