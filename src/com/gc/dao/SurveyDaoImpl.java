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
import com.gc.dto.SurveyDto;

/**
 * @author Beowolf
 *
 */
public class SurveyDaoImpl implements SurveyDao {

	/* (non-Javadoc)
	 * @see com.gc.dao.SurveyDao#getServID(com.gc.dto.SurveyDto)
	 */
	@Override
	public void getServID(SurveyDto servID) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.gc.dao.SurveyDao#getID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<CurrentScoreDto> getID(SurveyDto servID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(servID);
		tx.commit();
		session.close();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.SurveyDao#searchID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<CurrentScoreDto> searchID(SurveyDto servID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.SurveyDao#unpdateID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<CurrentScoreDto> unpdateID(SurveyDto servID) {
		// TODO Auto-generated method stub
		return null;
	}

}
