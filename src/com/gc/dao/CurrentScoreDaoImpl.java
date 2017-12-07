/**
 * 
 */
package com.gc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gc.dto.CurrentScoreDto;
import com.gc.dto.RestaurantDto;

/**
 * @author Serhiy Bardysh
 *
 */
public class CurrentScoreDaoImpl implements CurrentScoreDao {

	/* (non-Javadoc)
	 * @see com.gc.dao.CurrentScoreDao#getRestID(com.gc.dto.CurrentScoreDto)
	 */
	@Override
	public void getRestID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.gc.dao.CurrentScoreDao#getID(com.gc.dto.CurrentScoreDto)
	 */
	@Override
	public List<CurrentScoreDto> getID(CurrentScoreDto restID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(restID);
		tx.commit();
		session.close();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.CurrentScoreDao#searchID(com.gc.dto.CurrentScoreDto)
	 */
	@Override
	public List<CurrentScoreDto> searchID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.CurrentScoreDao#unpdateID(com.gc.dto.CurrentScoreDto)
	 */
	@Override
	public List<CurrentScoreDto> updateID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrentScoreDto> addcurrentScore(CurrentScoreDto scoredto, int totalscore, int restID) {
		List<CurrentScoreDto> scoreList = new ArrayList<CurrentScoreDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		CurrentScoreDto newScoreDto = new CurrentScoreDto();
		
		newScoreDto.setRestaurantID(restID);
		newScoreDto.setTotalScore(totalscore);
		
		session.save(scoredto);
		tx.commit();
		session.close();
		return scoreList;
	}

	

}