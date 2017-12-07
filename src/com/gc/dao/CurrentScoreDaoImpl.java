/**
 * 
 */
package com.gc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gc.dto.CurrentScoreDto;

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
	public List<CurrentScoreDto> unpdateID(CurrentScoreDto restID) {
		// TODO Auto-generated method stub
		return null;
	}

}
