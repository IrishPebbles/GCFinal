package com.gc.dto;

import java.io.Serializable;

public class PersonDto  implements  Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int userID;
		private String userEmail;
		private String userPass;
		
	public	PersonDto(){
	}

	public PersonDto(int userID, String userEmail, String userPass) {
		super();
		this.userID = userID;
		this.userEmail = userEmail;
		this.userPass = userPass;
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

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public String toString() {
		return "PersonDto [userID=" + userID + ", userEmail=" + userEmail + ", userPass=" + userPass + "]";
	}
	
	
}
