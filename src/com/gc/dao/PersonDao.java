/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.PersonDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface PersonDao {
	public void getUserID(PersonDto userID); 
	// TODO Auto-generated method stub

List<PersonDto> addID(String userEmail, String userPassword);
	

List<PersonDto> searchID(PersonDto userID);

List<PersonDto> getID(PersonDto userID, String userEmail, String userPassword);
	

List<PersonDto> updateID(PersonDto userID);
	// TODO Auto-generated method stub

}
