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
	List<SurveyDto> addSurvey(int restID, boolean hasVoted);

	List<SurveyDto> getID(SurveyDto servID);
	// TODO Auto-generated method stub

	List<SurveyDto> searchID(SurveyDto servID);
	// TODO Auto-generated method stub

	List<SurveyDto> unpdateID(SurveyDto servID);
	// TODO Auto-generated method stub
}
