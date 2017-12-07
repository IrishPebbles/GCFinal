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

/**
 * @author Serhiy Bardysh
 *
 */
public class AttendeesDaoImpl implements AttendeesDao {

	
	@Override
	public List<AttendeesDto> addNewID(AttendeesDto newUser, int userID, int outingID) {
		List<AttendeesDto> attenList = new ArrayList<AttendeesDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		AttendeesDto newAttenDto = new AttendeesDto();

		newAttenDto.setPersonID(userID);
		newAttenDto.setOutingID(outingID);

		session.save(newUser);
		tx.commit();
		session.close();
		return attenList;
	}
	
	@Override
	public List<AttendeesDto> getID(AttendeesDto newUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(AttendeesDto.class);
		ArrayList<AttendeesDto> getList = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return getList;
	
	}
	
	@Override
	public List<AttendeesDto> searchID(AttendeesDto newUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.like("attendeesID", "id"));

		ArrayList<AttendeesDto> attendeesList = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return attendeesList;
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

}
