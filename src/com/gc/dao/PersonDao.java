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
	List<PersonDto> getPerson(int userID); 
	// TODO Auto-generated method stub

List<PersonDto> addPerson(String userEmail, String userPassword);
	



List<PersonDto> searchByEmail(String userEmail);
	

List<PersonDto> updateID(PersonDto userID);
	// TODO Auto-generated method stub

}
