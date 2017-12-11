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
	public void getSurvID(SurveyDto survID);

	// TODO Auto-generated method stub
	List<SurveyDto> addSurvey(String restID1, String restID2, String restID3, String restID4, String restID5, int voteCount1, int voteCount2, int voteCount3, int voteCount4, int voteCount5, boolean hasVoted);

	List<SurveyDto> searchSurvey(int survID);
	// TODO Auto-generated method stub

	List<SurveyDto> searchID(SurveyDto survID);
	// TODO Auto-generated method stub

	List<SurveyDto> updateID(SurveyDto survID);
	// TODO Auto-generated method stub
}
