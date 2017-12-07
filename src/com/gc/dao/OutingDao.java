/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.CurrentScoreDto;
import com.gc.dto.OutingDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface OutingDao {
	public void getOutingID(OutingDto outingID); 
	// TODO Auto-generated method stub

List<CurrentScoreDto> getID(OutingDto outingID);
	// TODO Auto-generated method stub

List<CurrentScoreDto> searchID(OutingDto outingID);
	// TODO Auto-generated method stub


List<CurrentScoreDto> unpdateID(OutingDto outingID);
	// TODO Auto-generated method stub

}
