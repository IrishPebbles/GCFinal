package com.gc.util;

import java.util.ArrayList;

public class AttendeeList {
	
	private ArrayList<Person> attendees;
	private ArrayList<String> initialEmails;//we need this for the wrapper class to pull the model attribute
	public ArrayList<Person> getAttendees() {
		return attendees;
	}
	public void setAttendees(ArrayList<Person> attendees) {
		this.attendees = attendees;
	}
	public ArrayList<String> getInitialEmails() {
		return initialEmails;
	}
	public void setInitialEmails(ArrayList<String> initialEmails) {
		this.initialEmails = initialEmails;
	}
	
	
}
