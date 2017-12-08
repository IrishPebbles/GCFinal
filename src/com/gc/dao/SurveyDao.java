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
	List<SurveyDto> addSurvey(int restID1, int restID2, int restID3, int restID4, int restID5, int voteCount1, int voteCount2, int voteCount3, int voteCount4, int voteCount5, boolean hasVoted);

	List<SurveyDto> searchSurvey(int servID);
	// TODO Auto-generated method stub

	List<SurveyDto> searchID(SurveyDto servID);
	// TODO Auto-generated method stub

	List<SurveyDto> unpdateID(SurveyDto servID);
	// TODO Auto-generated method stub
}
