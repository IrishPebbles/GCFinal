package com.gc.dto;

import java.io.Serializable;

public class PersonDto  implements  Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int userID;
		private String userEmail;
		private String userPassword;
		
	public	PersonDto(){
	}

	public PersonDto(int userID, String userEmail, String userPassword) {
		super();
		this.userID = userID;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "PersonDto [userID=" + userID + ", userEmail=" + userEmail + ", userPass=" + userPassword + "]";
	}
	
	
}
