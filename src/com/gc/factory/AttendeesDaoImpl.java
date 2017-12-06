/**
 * 
 */
package com.gc.factory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.gc.dto.AttendeesDto;

/**
 * @author Serhiy Bardysh
 *
 */
public class AttendeesDaoImpl implements AttendeesDao {

	/* (non-Javadoc)
	 * @see com.gc.factory.AttendeesDao#addUser(com.gc.dto.AttendeesDto)
	 */
	@Override
	public void addUser(AttendeesDto newUser) {
		
	}

	/* (non-Javadoc)
	 * @see com.gc.factory.AttendeesDao#addNewID(com.gc.dto.AttendeesDto)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see com.gc.factory.AttendeesDao#getID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> getID(AttendeesDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.factory.AttendeesDao#searchID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> searchID(AttendeesDto newUser) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(AttendeesDto.class);

		crit.add(Restrictions.like("code", " " ));

		ArrayList<AttendeesDto> list = (ArrayList<AttendeesDto>) crit.list();
		tx.commit();
		session.close();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.factory.AttendeesDao#unpdateID(com.gc.dto.AttendeesDto)
	 */
	@Override
	public List<AttendeesDto> unpdateID(AttendeesDto newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
