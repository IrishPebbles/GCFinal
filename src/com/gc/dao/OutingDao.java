/**
 * 
 */
package com.gc.dao;

import java.util.Date; 
import java.util.List;

import com.gc.dto.OutingDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface OutingDao {
	List<OutingDto> getOutingID(int outingID); 
	// TODO Auto-generated method stub
	
List<OutingDto> addOuting(String outingName, String surveyID, Date dateOfEvent, String finalLoc, int organizer);

List<OutingDto> searchID(OutingDto outingID);
	// TODO Auto-generated method stub


List<OutingDto> updateID(OutingDto outingID);
	// TODO Auto-generated method stub

List<OutingDto> searchSurveyID(String surveyID);

}
