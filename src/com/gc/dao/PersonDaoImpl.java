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

import com.gc.dto.CurrentScoreDto;

import com.gc.dto.PersonDto;


/**
 * @author Serhiy Bardysh
 *
 */
public class PersonDaoImpl implements PersonDao {

	/* (non-Javadoc)
	 * @see com.gc.dao.PersonDao#getUserID(com.gc.dto.PersonDto)
	 */
	@Override
	public void getUserID(PersonDto userID) {
		// TODO Auto-generated method stub

	}

	@Override

	public List<PersonDto> addID(PersonDto userID, String userEmail, String userPassword) {
	

		List<PersonDto> restList = new ArrayList<PersonDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		PersonDto newPersonDto = new PersonDto();
		
		newPersonDto.setUserEmail(userEmail);
		newPersonDto.setUserPass(userPassword);
		
		session.save(userID);
		tx.commit();
		session.close();
	
		return restList;
	}

	
	@Override
	public List<PersonDto> searchID(PersonDto userID) {
		
	
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(PersonDto.class);

		crit.add(Restrictions.like("userID",  userID));

		ArrayList<PersonDto> personList = (ArrayList<PersonDto>) crit.list();
		tx.commit();
		session.close();

		return personList;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.PersonDao#unpdateID(com.gc.dto.PersonDto)
	 */
	@Override
	public List<PersonDto> unpdateID(PersonDto userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonDto> getID(PersonDto userID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(PersonDto.class);
		ArrayList<PersonDto> getList = (ArrayList<PersonDto>) crit.list();
		tx.commit();
		session.close();
		return getList;
		
	}

	@Override
	public List<PersonDto> addID(PersonDto userID, String userEmail, String userPassword) {
		// TODO Auto-generated method stub
		return null;
	}

}
