/**
 * 
 */
package com.gc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.gc.dto.CurrentScoreDto;
import com.gc.dto.PersonDto;
import com.gc.dto.RestaurantDto;

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

	/* (non-Javadoc)
	 * @see com.gc.dao.PersonDao#getID(com.gc.dto.PersonDto)
	 */
	@Override
	public List<PersonDto> getID(PersonDto userID, String userEmail, String userPassword) {
	
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

	/* (non-Javadoc)
	 * @see com.gc.dao.PersonDao#searchID(com.gc.dto.PersonDto)
	 */
	@Override
	public List<CurrentScoreDto> searchID(PersonDto userID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.PersonDao#unpdateID(com.gc.dto.PersonDto)
	 */
	@Override
	public List<CurrentScoreDto> unpdateID(PersonDto userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
