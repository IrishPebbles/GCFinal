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
import com.gc.util.HibernateUtil;


/**
 * @author Serhiy Bardysh
 *
 */
public class PersonDaoImpl implements PersonDao {
	private static SessionFactory sessionFactory;
	
	 public PersonDaoImpl() {
		 sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override

	public List<PersonDto> addPerson(String userEmail, String userPassword) {

		List<PersonDto> restList = new ArrayList<PersonDto>();
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
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(PersonDto.class);
		
		crit.add(Restrictions.eq("userEmail", userEmail));
	
		ArrayList<PersonDto> getEmail = (ArrayList<PersonDto>) crit.list();
		
		//System.out.println("Looking up" + getEmail.get(0).toString());
		tx.commit();
		session.close();
		return getEmail;
		
	}
		public void  updatePassword(PersonDto person) {
		

			Session session = sessionFactory.openSession();

			session.beginTransaction();
			

			session.update(person); // update the object from the list

			session.getTransaction().commit(); // update the row from the database table
			
			session.close();

		}


	@Override
	public List<PersonDto> searchByPassword(String userPassword) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(PersonDto.class);
		
		crit.add(Restrictions.eq("userPassword", userPassword));
		ArrayList<PersonDto> getPassword = (ArrayList<PersonDto>) crit.list();
		
		//System.out.println("Looking up" + getEmail.get(0).toString());
		tx.commit();
		session.close();
		return getPassword;
	}

}