/**
 * 
 */
package com.gc.dao;

import java.sql.Date; 
import java.util.List;

import com.gc.dto.OutingDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface OutingDao {
	List<OutingDto> getOutingID(int outingID); 
	// TODO Auto-generated method stub
	
List<OutingDto> addOuting(String outingName, Date dateOfEvent, String finalLoc, int organize);

List<OutingDto> searchID(OutingDto outingID);
	// TODO Auto-generated method stub


List<OutingDto> updateID(OutingDto outingID);
	// TODO Auto-generated method stub

}
