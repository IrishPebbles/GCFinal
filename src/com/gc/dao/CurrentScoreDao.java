/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.CurrentScoreDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface CurrentScoreDao {
	public void getRestID(CurrentScoreDto restID); 
		// TODO Auto-generated method stub

	List<CurrentScoreDto> getID(CurrentScoreDto restID);
		// TODO Auto-generated method stub
	
	List<CurrentScoreDto> addcurrentScore(int totalscore, int restID);

	List<CurrentScoreDto> searchID(CurrentScoreDto restID);
		// TODO Auto-generated method stub
	

	List<CurrentScoreDto> updateID(CurrentScoreDto restID);
		// TODO Auto-generated method stub

	

}
