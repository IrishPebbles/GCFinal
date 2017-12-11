package com.gc.util;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.gc.dao.PersonDaoImpl;
import com.gc.dto.PersonDto;

import antlr.collections.List;

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
	
	public void login(String typedPassword) {
		//check database for user
		PersonDaoImpl databaseConnection = new PersonDaoImpl();
		//new implementation of database
		ArrayList<PersonDto> searchedPerson = (ArrayList<PersonDto>) databaseConnection.searchByEmail(userEmail);
		//search email
		
		if (searchedPerson != null) {
			PersonDto locatedPerson = searchedPerson.get(0);
			String userPassword = locatedPerson.getUserPassword();
			// I need to check to password in database
			
			if (userPassword == generateHashPassword(typedPassword)) {
			// if they match user loged in
			}
		}else {
			// we need to create a person or force them to register
			
		}
				
	}

	public static String generateHashPassword(String typedPassword) { // String returns success or failure msg 
		
		String pw_hash = BCrypt.hashpw(typedPassword, BCrypt.gensalt(12));
				
		return pw_hash;
		
	}
		
}