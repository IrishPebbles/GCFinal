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
	List<SurveyDto> getSurvID(SurveyDto survID);

	// TODO Auto-generated method stub
	List<SurveyDto> addSurvey(String surveyID, String restID1, String restID2, String restID3, String restID4, String restID5, int voteCount1, int voteCount2, int voteCount3, int voteCount4, int voteCount5, boolean hasVoted);

	List<SurveyDto> searchSurvey(String survID);
	// TODO Auto-generated method stub

	List<SurveyDto> searchID(SurveyDto survID);
	// TODO Auto-generated method stub

	List<SurveyDto> updateSurvey(SurveyDto survID);
	// TODO Auto-generated method stub
}
