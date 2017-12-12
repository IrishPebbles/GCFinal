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
	public void getSurvID(SurveyDto survID) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.dao.SurveyDao#getID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> addSurvey(String surveyID, String restID1, String restID2, String restID3, String restID4, String restID5, int voteCount1, int voteCount2, int voteCount3, int voteCount4, int voteCount5, boolean hasVoted) {
		List<SurveyDto> surveyList = new ArrayList<SurveyDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SurveyDto newSurvDto = new SurveyDto();

	    newSurvDto.setSurveyID(surveyID);
		newSurvDto.setOptVenueID1(restID1);
		newSurvDto.setOptVenueID2(restID2);
		newSurvDto.setOptVenueID3(restID3);
		newSurvDto.setOptVenueID4(restID4);
		newSurvDto.setOptVenueID5(restID5);
		newSurvDto.setVoteCount1(voteCount1);
		newSurvDto.setVoteCount2(voteCount2);
		newSurvDto.setVoteCount3(voteCount3);
		newSurvDto.setVoteCount4(voteCount4);
		newSurvDto.setVoteCount5(voteCount5);
		
		newSurvDto.setHasVoted(hasVoted);
		
		session.save(newSurvDto);
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
	public List<SurveyDto> searchSurvey(String surveyID) {
		System.out.println("In beginning of method" + surveyID);

		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(SurveyDto.class);

		crit.add(Restrictions.eq("surveyID", surveyID));

		ArrayList<SurveyDto> surveyList = (ArrayList<SurveyDto>) crit.list();
		tx.commit();
		session.close();
		System.out.println("Is it null ?" + surveyList.isEmpty());
		return surveyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.dao.SurveyDao#unpdateID(com.gc.dto.SurveyDto)
	 */
	@Override
	public List<SurveyDto> updateID(SurveyDto survID) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<SurveyDto> searchID(SurveyDto survID) {
		// TODO Auto-generated method stub
		return null;
	}

}
