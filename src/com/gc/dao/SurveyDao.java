/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.CurrentScoreDto;
import com.gc.dto.SurveyDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface SurveyDao {
	public void getServID(SurveyDto servID); 
	// TODO Auto-generated method stub

List<CurrentScoreDto> getID(SurveyDto servID);
	// TODO Auto-generated method stub

List<CurrentScoreDto> searchID(SurveyDto servID);
	// TODO Auto-generated method stub

List<CurrentScoreDto> unpdateID(SurveyDto servID);
	// TODO Auto-generated method stub
}
