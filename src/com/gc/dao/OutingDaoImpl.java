package com.gc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gc.dto.CurrentScoreDto;
import com.gc.dto.OutingDto;

public class OutingDaoImpl implements OutingDao {

	@Override
	public void getOutingID(OutingDto outingID) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CurrentScoreDto> getID(OutingDto outingID) {
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
	public List<CurrentScoreDto> searchID(OutingDto outingID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrentScoreDto> unpdateID(OutingDto outingID) {
		// TODO Auto-generated method stub
		return null;
	}

}
