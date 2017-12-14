package com.gc.util;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.gc.dao.PersonDaoImpl;
import com.gc.dto.PersonDto;

import antlr.collections.List;

public class Person {

	private int personID;
	private String userEmail; // Used as user name
	private String password;
	private ArrayList<Outing> createdOutings;
	
	public Person() {
		
	}
	
	
	public Person(String userEmail, String password) {
		super();
		this.userEmail = userEmail;
		this.password = password;
	}
	
	
	public Person(PersonDto dtoObj) {
		userEmail = dtoObj.getUserEmail();
		password = dtoObj.getUserPassword();
		personID= dtoObj.getUserID();
	}
	
	
	public int getPersonID() {
		return personID;
	}


	public void setPersonID(int personID) {
		this.personID = personID;
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
	
	/*public void login(String typedPassword) {
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
				
	}*/

	public static String generateHashPassword(String typedPassword) { // String returns success or failure msg 
		
		String pw_hash = BCrypt.hashpw(typedPassword, BCrypt.gensalt(12));
				
		return pw_hash;
		
	}
	
	public static String checkUserGenerateHTML (String searchEmail) {
		PersonDaoImpl pdao = new PersonDaoImpl();
		ArrayList<PersonDto> user  =  (ArrayList<PersonDto>) pdao.searchByEmail(searchEmail); // we need to enter if statement to count for userEmail not found
		
		String userLoginText = "";
		if (!user.isEmpty()) {
			PersonDto searchUser = user.get(0); // getting userEmail from ArrayList<PersonDto> at location zero
			userLoginText =" <h2> Welcome " + searchEmail+ "</h2> Your user name: <input type=\"email\" name=\"voterEmail\"value=\"" + searchEmail + "\"><br><br> Please enter your password:  <input type=\"password\"name=\"passwordBox1\">";
			
		}
			
		else {
		userLoginText = "<p > You do not have an account associated with  "+ searchEmail + ". </p>" +"Please create an account below: </p> Your email: <input type=\"email\"name=\"voterEmail\"value=\"" + searchEmail + "\"><br><br> Please enter your password:     <input type=\"password\"name=\"passwordBox1\"><br> <br> Please Re-enter password your: <input type=\"password\"name=\"passwordBox2\"> <br><br> "; 
			
			// what we want to do if they don't have account created. 
			pdao.addPerson(searchEmail, "1");
			}	
		
		return userLoginText; 
	}
	
	public static Person checkUserExistsOrCreate(String searchEmail) {
		PersonDaoImpl pdao = new PersonDaoImpl();
		ArrayList<PersonDto> user  =  (ArrayList<PersonDto>) pdao.searchByEmail(searchEmail); // we need to enter if statement to count for userEmail not found
		PersonDto searchUser;
		if (!user.isEmpty()) {
			 searchUser = user.get(0); // getting userEmail from ArrayList<PersonDto> at location zero

		}
			
		else {	
			// what we want to do if they don't have account created. 
			pdao.addPerson(searchEmail, "1");
			user  =  (ArrayList<PersonDto>) pdao.searchByEmail(searchEmail);
			searchUser = user.get(0);
			}	
		Person userBuiltFromTable = new Person(searchUser);
		
		return userBuiltFromTable;
	}
		
}