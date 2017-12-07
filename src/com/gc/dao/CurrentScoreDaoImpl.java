/**
 * 
 */
package com.gc.dao;

import java.util.ArrayList;  
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.gc.dto.CurrentScoreDto;

/**
 * @author Serhiy Bardysh
 *
 */
public class CurrentScoreDaoImpl implements CurrentScoreDao {

	
	@Override
	public void getRestID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub

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
	
	@Override
	public List<CurrentScoreDto> getID(CurrentScoreDto restID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(CurrentScoreDto.class);
		ArrayList<CurrentScoreDto> getList = (ArrayList<CurrentScoreDto>) crit.list();
		tx.commit();
		session.close();
		return getList;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.CurrentScoreDao#searchID(com.gc.dto.CurrentScoreDto)
	 */
	@Override
	public List<CurrentScoreDto> searchID(CurrentScoreDto restID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(CurrentScoreDto.class);

		crit.add(Restrictions.like("attendeesID", "id"));

		ArrayList<CurrentScoreDto> currentScoreList = (ArrayList<CurrentScoreDto>) crit.list();
		tx.commit();
		session.close();
		return currentScoreList;
		
	}

	
	@Override
	public List<CurrentScoreDto> updateID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

}