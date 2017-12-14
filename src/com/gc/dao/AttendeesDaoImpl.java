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
import com.gc.dto.SurveyDto;
import com.gc.util.HibernateUtil;

/**
 * @author Serhiy Bardysh
 *
 */
public class AttendeesDaoImpl implements AttendeesDao {
	 private static SessionFactory sessionFactory;
	 
	 public AttendeesDaoImpl() {
		 sessionFactory = HibernateUtil.getSessionFactory();
	 }
	
	//Working add function
	@Override
	public List<AttendeesDto> addNewAttendees(int userID, int outingID) {
		List<AttendeesDto> scoreList = new ArrayList<AttendeesDto>();
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

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.eq("attendeesID", attendeesID));

		ArrayList<AttendeesDto> list = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<AttendeesDto> searchByPersonIDAndOutID(int personID, int outingID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.eq("personID", personID));
		crit.add(Restrictions.eq("outingID", outingID));

		ArrayList<AttendeesDto> list = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<AttendeesDto> searchByPersonID(int personID) {

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.eq("personID", personID));

		ArrayList<AttendeesDto> list = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<AttendeesDto> searchByOutingID(int outingID) {

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.eq("outingID", outingID));

		ArrayList<AttendeesDto> list = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gc.factory.AttendeesDao#unpdateID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> updateAttendees(AttendeesDto newUser) {
		// TODO Auto-generated method stub@Override

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(newUser); // update the object from the list

			session.getTransaction().commit(); // update the row from the database table
			
			session.close();

		
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