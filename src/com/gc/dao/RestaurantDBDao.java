/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.RestaurantDto;

/**
 * @author Serhiy Bardysh
 *
 */
public interface RestaurantDBDao {
	public void getRestID(RestaurantDto restID); 
	// TODO Auto-generated method stub

List<RestaurantDto> addID(RestaurantDto restID, String restLocation, double restRating);
	// TODO Auto-generated method stub

List<RestaurantDto> searchID(RestaurantDto restID);

List<RestaurantDto> getID(RestaurantDto restID);
	// TODO Auto-generated method stub

List<RestaurantDto> unpdateID(RestaurantDto restID);
	// TODO Auto-generated method stub
}
