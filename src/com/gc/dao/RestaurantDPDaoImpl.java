
package com.gc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gc.dto.RestaurantDto;

/**
 * @author Serhiy Bardysh
 *
 */
public class RestaurantDPDaoImpl implements RestaurantDBDao {

	/* (non-Javadoc)
	 * @see com.gc.dao.RestaurantDBDao#getRestID(com.gc.dto.RestaurantDto)
	 */
	@Override
	public void getRestID(RestaurantDto restID) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.gc.dao.RestaurantDBDao#getID(com.gc.dto.RestaurantDto)
	 */
	@Override
	public List<RestaurantDto> addID(RestaurantDto restID, String restLocation, double restRating ) {
		
			
		List<RestaurantDto> restList = new ArrayList<RestaurantDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		RestaurantDto newRestDto = new RestaurantDto();
		
		newRestDto.setRestaurantLocation(restLocation);
		newRestDto.setRestaurantRating(restRating);
		
		session.save(restID);
		tx.commit();
		session.close();
		return restList;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.RestaurantDBDao#searchID(com.gc.dto.RestaurantDto)
	 */
	@Override
	public List<RestaurantDto> searchID(RestaurantDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.RestaurantDBDao#unpdateID(com.gc.dto.RestaurantDto)
	 */
	@Override
	public List<RestaurantDto> unpdateID(RestaurantDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

}
