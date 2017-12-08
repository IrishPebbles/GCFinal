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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.dao.SurveyDao#getServID(com.gc.dto.SurveyDto)
	 */
	@Override
	public void getServID(SurveyDto servID) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.dao.SurveyDao#getID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> addSurvey(String restID1, String restID2, String restID3, String restID4, String restID5, int voteCount1, int voteCount2, int voteCount3, int voteCount4, int voteCount5, boolean hasVoted) {
		List<SurveyDto> surveyList = new ArrayList<SurveyDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SurveyDto newServDto = new SurveyDto();

	
		newServDto.setOptVenueID1(restID1);
		newServDto.setOptVenueID2(restID2);
		newServDto.setOptVenueID3(restID3);
		newServDto.setOptVenueID4(restID4);
		newServDto.setOptVenueID5(restID5);
		newServDto.setVoteCount1(voteCount1);
		newServDto.setVoteCount2(voteCount2);
		newServDto.setVoteCount3(voteCount3);
		newServDto.setVoteCount4(voteCount4);
		newServDto.setVoteCount5(voteCount5);
		
		newServDto.setHasVoted(hasVoted);

		session.save(newServDto);
		tx.commit();
		session.close();
		return surveyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.dao.SurveyDao#searchID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> searchSurvey(int surveyID) {

		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(SurveyDto.class);

		crit.add(Restrictions.like("surveyID", surveyID));

		ArrayList<SurveyDto> surveyList = (ArrayList<SurveyDto>) crit.list();
		tx.commit();
		session.close();

		return surveyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.dao.SurveyDao#unpdateID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> unpdateID(SurveyDto servID) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
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

	}*/

	@Override
	public List<SurveyDto> searchID(SurveyDto servID) {
		// TODO Auto-generated method stub
		return null;
	}

}
