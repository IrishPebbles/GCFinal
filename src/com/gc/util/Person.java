package com.gc.util;
import java.util.ArrayList;

public class Person {

	private String userEmail; // Used as user name
	private String password;
	private ArrayList<Outing> createdOutings;
	
	public Person() {
		
	}
	
	
	public Person(String userEmail, String password, ArrayList<Outing> createdOutings) {
		super();
		this.userEmail = userEmail;
		this.password = password;
		this.createdOutings = createdOutings;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Outing> getCreatedOutings() {
		return createdOutings;
	}
	public void setCreatedOutings(ArrayList<Outing> createdOutings) {
		this.createdOutings = createdOutings;
	}
	
	public int getId(String userEmail) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "Person [userEmail=" + userEmail + ", password=" + password + ", createdOutings=" + createdOutings + "]";
	}

	public String generatePassword(String typedPassword) {
		
		
		
		return null;
		
	}
	
}