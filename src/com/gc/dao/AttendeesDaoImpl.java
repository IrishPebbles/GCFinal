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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.factory.AttendeesDao#addUser(com.gc.dto.AttendeesDto)
	 */
	/*@Override
	public List<AttendeesDto> addNewAttendee(AttendeesDto newUser, int userID, int outingID) {
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
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.factory.AttendeesDao#addNewID(com.gc.dto.AttendeesDto)
	 */

	public List<AttendeesDto> addNewID(AttendeesDto newUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(newUser);
		tx.commit();
		session.close();
		return null;
	}
	
	@Override
	public List<AttendeesDto> addNewAttendee(AttendeesDto newUser, int userID, int outingID) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.factory.AttendeesDao#searchID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> searchID(AttendeesDto newUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.like("attendeesID", "id"));

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

}