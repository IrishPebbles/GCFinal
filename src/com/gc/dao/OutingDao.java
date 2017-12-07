/**
 * 
 */
package com.gc.dao;

import java.sql.Date;
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
	
List<OutingDto> addOuting(OutingDto outingDto, String outingName, Date dateOfEvent, String finalLoc, int organize);

List<OutingDto> getID(OutingDto outingID);
	// TODO Auto-generated method stub

List<OutingDto> searchID(OutingDto outingID);
	// TODO Auto-generated method stub


List<OutingDto> updateID(OutingDto outingID);
	// TODO Auto-generated method stub

}
