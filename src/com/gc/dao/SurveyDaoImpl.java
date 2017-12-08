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

 import com.gc.dto.SurveyDto;

/**
 * @author Serhiy Bardysh
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
	public List<SurveyDto> addSurvey(int restID, boolean hasVoted) {
		List<SurveyDto> serveyList = new ArrayList<SurveyDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SurveyDto newServDto = new SurveyDto();
		
		newServDto.setfinalVenueID(restID);
		newServDto.setOptVenueID1(restID);
		newServDto.setOptVenueID2(restID);
		newServDto.setOptVenueID3(restID);
		newServDto.setOptVenueID4(restID);
		newServDto.setOptVenueID5(restID);
		newServDto.setHasVoted(hasVoted);

		
		session.save(restID);
		tx.commit();
		session.close();
		return serveyList;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.SurveyDao#searchID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> searchID(SurveyDto servID) {
		
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(SurveyDto.class);

		crit.add(Restrictions.like("servID",  servID));

		ArrayList<SurveyDto> serveyList = (ArrayList<SurveyDto>) crit.list();
		tx.commit();
		session.close();

		return serveyList;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.SurveyDao#unpdateID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> unpdateID(SurveyDto servID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyDto> getID(SurveyDto servID) {
		
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(SurveyDto.class);
		ArrayList<SurveyDto> getList = (ArrayList<SurveyDto>) crit.list();
		tx.commit();
		session.close();
		return getList;
		
	}

}
