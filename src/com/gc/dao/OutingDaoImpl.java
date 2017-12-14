package com.gc.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.gc.dto.AttendeesDto;
import com.gc.dto.OutingDto;
import com.gc.dto.PersonDto;
import com.gc.util.HibernateUtil;

public class OutingDaoImpl implements OutingDao {
	
	private static SessionFactory sessionFactory;
	
	public OutingDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public List<OutingDto> addOuting(String outingName, String surveyID, Date dateOfEvent, String finalLoc, int organizer){
		
		List<OutingDto> outingList = new ArrayList<OutingDto>();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		OutingDto newOuting = new OutingDto();
	
		newOuting.setDateOfEvent(dateOfEvent);
		newOuting.setFinalLocation(finalLoc);
		newOuting.setOrganizer(14);
		newOuting.setOutingName(outingName);
		newOuting.setSurveyID(surveyID);
		
		session.save(newOuting);
		tx.commit();
		session.close();
		return outingList;
	}

	@Override
	public List<OutingDto> getOutingID(int outingID) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(OutingDto.class);
		crit.add(Restrictions.eq("outingID", outingID));

		ArrayList<OutingDto> getList = (ArrayList<OutingDto>) crit.list();
		tx.commit();
		session.close();
		return getList;
	}

	@Override
	public List<OutingDto> searchID(OutingDto outingID) {

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(OutingDto.class);

		crit.add(Restrictions.like("outingID",  outingID));

		ArrayList<OutingDto> personList = (ArrayList<OutingDto>) crit.list();
		tx.commit();
		session.close();

		return personList;
		
	}

	@Override
	public List<OutingDto> updateID(OutingDto outingID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<OutingDto> searchSurveyID(String surveyID) {

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(OutingDto.class);

		crit.add(Restrictions.like("surveyID",  surveyID));

		ArrayList<OutingDto> surveyList = (ArrayList<OutingDto>) crit.list();
		tx.commit();
		session.close();

		return surveyList;
	}
}
