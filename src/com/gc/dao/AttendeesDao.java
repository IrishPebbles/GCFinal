package com.gc.dao;

import java.util.List;

import com.gc.dto.AttendeesDto;

public interface AttendeesDao {
	void addUser (AttendeesDto newUser);
	
	List<AttendeesDto> addNewID (AttendeesDto newUser);
	// adding new ID
	
	List<AttendeesDto> searchID (AttendeesDto newUser);
	// searching ID
	
	List<AttendeesDto> unpdateID (AttendeesDto newUser);
	// updating ID in case Attendees will become organizer
	
	
}
