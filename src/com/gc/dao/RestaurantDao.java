/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.RestaurantDto;

/**
 * @author Serhiy Barydsh
 *
 */
public interface RestaurantDao {

	List<RestaurantDto> getList(double d, double e, double f);

}
