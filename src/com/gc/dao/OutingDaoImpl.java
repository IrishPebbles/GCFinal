package com.gc.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.gc.dto.OutingDto;
import com.gc.dto.PersonDto;

public class OutingDaoImpl implements OutingDao {

	@Override
	public List<OutingDto> addOuting(OutingDto outingDto, String outingName, Date dateOfEvent, String finalLoc, int organizer){
		
		List<OutingDto> outingList = new ArrayList<OutingDto>();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		OutingDto newOutingDto = new OutingDto();
		
		newOutingDto.setOutingName(outingName);
		newOutingDto.setDateOfEvent(dateOfEvent); 
		newOutingDto.setFinalLocation(finalLoc);
		newOutingDto.setOrganizer(organizer); 
		
		session.save(outingDto);
		tx.commit();
		session.close();
		return outingList;
	}

	@Override
	public List<OutingDto> getID(OutingDto outingID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(OutingDto.class);
		ArrayList<OutingDto> getList = (ArrayList<OutingDto>) crit.list();
		tx.commit();
		session.close();
		return getList;
	}

	@Override
	public List<OutingDto> searchID(OutingDto outingID) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

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
	public void getOutingID(OutingDto outingID) {
		// TODO Auto-generated method stub
		
	}

}
