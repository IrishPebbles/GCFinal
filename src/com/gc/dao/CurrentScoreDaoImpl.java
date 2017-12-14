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
import com.gc.dto.PersonDto;
import com.gc.util.HibernateUtil;

/**
 * @author Serhiy Bardysh
 *
 */
public class CurrentScoreDaoImpl implements CurrentScoreDao {
	
	private static SessionFactory sessionFactory;
	 
	 public CurrentScoreDaoImpl() {
		// TODO Auto-generated constructor stub
		 sessionFactory = HibernateUtil.getSessionFactory();
	 }
	
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
	public List<CurrentScoreDto> getCurrentScore(int currentScoreID) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(CurrentScoreDto.class);
		crit.add(Restrictions.eq("currentScoreID", currentScoreID));
		ArrayList<CurrentScoreDto> currentScoreList = (ArrayList<CurrentScoreDto>) crit.list();
		tx.commit();
		session.close();
		return currentScoreList;
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
	public List<CurrentScoreDto> addcurrentScore(int totalscore, int restID) {
		List<CurrentScoreDto> scoreList = new ArrayList<CurrentScoreDto>();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		CurrentScoreDto newScoreDto = new CurrentScoreDto();
		
		newScoreDto.setRestaurantID(restID);
		newScoreDto.setTotalScore(totalscore);
		
		session.save(newScoreDto);
		tx.commit();
		session.close();
		return scoreList;
	}

	@Override
	public List<CurrentScoreDto> searchID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}