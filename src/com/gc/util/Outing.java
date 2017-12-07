package com.gc.util;
import java.util.ArrayList;

public class Outing {
	private String dateOfEvent;
	private String timeOfEvent;
	private RestaurantObj finalLocation;
	private Person organizer;
	private ArrayList<Person> attendees;

	public Outing() {

	}

	public Outing(String dateOfEvent, String timeOfEvent, RestaurantObj finalLocation, Person organizer,
			ArrayList<Person> attendees) {
		super();
		this.dateOfEvent = dateOfEvent;
		this.timeOfEvent = timeOfEvent;
		this.finalLocation = finalLocation;
		this.organizer = organizer;
		this.attendees = attendees;
	}

	public String getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(String dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String getTimeOfEvent() {
		return timeOfEvent;
	}

	public void setTimeOfEvent(String timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
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
		return "Outing [dateOfEvent=" + dateOfEvent + ", timeOfEvent=" + timeOfEvent + ", finalLocation="
				+ finalLocation + ", organizer=" + organizer + ", attendees=" + attendees + "]";
	}

	public void setSearchRadius() {

	}

	public void setVotingTimeLimit() {
		// TODO create method that allows user to set time limit to end voting
	}

}