/** 
 * 
 */
package com.gc.dao;

import java.util.ArrayList; 
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Restrictions;

import com.gc.dto.AttendeesDto;
import com.gc.dto.CurrentScoreDto;

/**
 * @author Serhiy Bardysh
 *
 */
public class AttendeesDaoImpl implements AttendeesDao {

	
	//Working add function
	@Override
	public List<AttendeesDto> addNewAttendees(int userID, int outingID) {
		List<AttendeesDto> scoreList = new ArrayList<AttendeesDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		AttendeesDto newAttendees = new AttendeesDto();
		
		newAttendees.setOutingID(outingID);
		newAttendees.setPersonID(userID);
		
		session.save(newAttendees);
		tx.commit();
		session.close();
		return scoreList;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.factory.AttendeesDao#searchID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> searchID(int attendeesID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		//crit.add(Restrictions.like());

		ArrayList<AttendeesDto> list = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.factory.AttendeesDao#unpdateID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> unpdateID(AttendeesDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void getUserID(AttendeesDto attenID) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<AttendeesDto> getID(AttendeesDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}