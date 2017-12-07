/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.CurrentScoreDto;
import com.gc.dto.PersonDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface PersonDao {
	public void getUserID(PersonDto userID); 
	// TODO Auto-generated method stub

List<CurrentScoreDto> getID(PersonDto userID);
	// TODO Auto-generated method stub

List<CurrentScoreDto> searchID(PersonDto userID);
	// TODO Auto-generated method stub

List<CurrentScoreDto> unpdateID(PersonDto userID);
	// TODO Auto-generated method stub
}
