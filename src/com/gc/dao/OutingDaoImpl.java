package com.gc.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gc.dto.OutingDto;

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
		session.save(outingID);
		tx.commit();
		session.close();
		return null;
	}

	@Override
	public List<OutingDto> searchID(OutingDto outingID) {
		// TODO Auto-generated method stub
		return null;
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
