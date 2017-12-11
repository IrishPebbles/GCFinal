package com.gc.util;
import java.util.ArrayList;
import java.util.Date;

public class Outing {

	private Date dateOfEvent;
	private RestaurantObj finalLocation;
	private Person organizer;
	private ArrayList<Person> attendees;
	private Survey potentialEvent;
	private GeolocationAPI location;
	private String outingName;

	public Outing() {

	}


	public Outing(Date dateOfEvent, GeolocationAPI location , Person organizer,
			ArrayList<Person> attendees) {
		
		this.dateOfEvent = dateOfEvent;
		this.finalLocation = finalLocation;
		this.organizer = organizer;
		this.attendees = attendees;
		this.location = location;
		potentialEvent = new Survey(); //every time we instantiate a new Outing object a survey is auto-created
		potentialEvent.setVoters(attendees);
		potentialEvent.createPotentialList(location);
		
		
		
	}

	public Survey getPotentialEvent() {
		return potentialEvent;
	}


	public void setPotentialEvent(Survey potentialEvent) {
		this.potentialEvent = potentialEvent;
	}


	public GeolocationAPI getLocation() {
		return location;
	}


	public void setLocation(GeolocationAPI location) {
		this.location = location;
	}


	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	
	public RestaurantObj getFinalLocation() {
	
		return finalLocation;
	}

	public void setFinalLocation(RestaurantObj finalLocation) {
		this.finalLocation = finalLocation;
	}

	public Person getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Person organizer) {
		this.organizer = organizer;
	}

	public ArrayList<Person> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<Person> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		return "Outing [dateOfEvent=" + dateOfEvent + ", finalLocation="
				+ finalLocation + ", organizer=" + organizer + ", attendees=" + attendees + "]";
	}

	public void setSearchRadius() {

	}

	public void setVotingTimeLimit() {
		// TODO create method that allows user to set time limit to end voting
	}

}