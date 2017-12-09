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

import com.gc.dto.PersonDto;


/**
 * @author Serhiy Bardysh
 *
 */
public class PersonDaoImpl implements PersonDao {



	@Override

	public List<PersonDto> addPerson(String userEmail, String userPassword) {
	

		List<PersonDto> restList = new ArrayList<PersonDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		PersonDto newPersonDto = new PersonDto();
		
		newPersonDto.setUserEmail(userEmail);
		newPersonDto.setUserPassword(userPassword);
		
		session.save(newPersonDto);
		tx.commit();
		session.close();
	
		return restList;
	}

	
	@Override
	public List<PersonDto> getPerson(int userID) {
		
	
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(PersonDto.class);

		crit.add(Restrictions.eq("userID", userID));

		ArrayList<PersonDto> personList = (ArrayList<PersonDto>) crit.list();
		tx.commit();
		session.close();

		return personList;
	}

	
	@Override
	public List<PersonDto> updateID(PersonDto userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonDto> searchByEmail(String userEmail) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(PersonDto.class);
		
		crit.add(Restrictions.eq("userEmail", userEmail));
		
		ArrayList<PersonDto> getEmail = (ArrayList<PersonDto>) crit.list();
		
		System.out.println("Looking up" + getEmail.get(0).toString());
		tx.commit();
		session.close();
		return getEmail;
		
	}

}