/**
 * 
 */
package com.gc.dao;

import java.util.ArrayList;

import org.apache.catalina.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gc.dto.CurrentScoreDto;

/**
 * @author James Burger
 *
 */
public class currentScoreDaoImpl implements currentScoreDao {

	@Override
	public void addcurrentScore(CurrentScoreDto newscore){

		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(newscore);
		tx.commit();
		session.close();
	}

	@RequestMapping(value = "searchbyproduct", method = RequestMethod.GET)
	public ModelAndView searchProduct(@RequestParam("product") String prod) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(ProductDto.class);

		crit.add(Restrictions.like("code", "%" + prod + "%"));

		ArrayList<ProductDto> list = (ArrayList<ProductDto>) crit.list();
		tx.commit();
		session.close();

		return new ModelAndView("welcome", "message", list);

	}

	@RequestMapping("/update")
	public ModelAndView updateForm(@RequestParam("id") int id) {

		return new ModelAndView("updateprodform", "productID", id);
	}
	
	@RequestMapping("/updateproduct")
	public ModelAndView updateProduct(@RequestParam("id") int id, @RequestParam("code") String code,
			@RequestParam("description") String desc, @RequestParam("listPrice") double price) {

		// temp Object will store info for the object we want to update
		ProductDto temp = new ProductDto();
		// by passing in the product id from a hidden field we can determine what row to edit
		temp.setProductID(id);
		temp.setCode(code);
		temp.setDescription(desc);
		temp.setListPrice(price);

		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFact = cfg.buildSessionFactory();

		Session codes = sessionFact.openSession();

		codes.beginTransaction();

		codes.update(temp); // update the object from the list

		codes.getTransaction().commit(); // update the row from the database table

		ArrayList<ProductDto> prodList = getAllProducts();

		return new ModelAndView("welcome", "message", prodList);
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteCustomer(@RequestParam("id") int id) {

		// temp Object will store info for the object we want to delete
		ProductDto temp = new ProductDto();
		temp.setProductID(id);

		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFact = cfg.buildSessionFactory();

		Session codes = sessionFact.openSession();

		codes.beginTransaction();

		codes.delete(temp); // delete the object from the list

		codes.getTransaction().commit(); // delete the row from the database table

		ArrayList<ProductDto> prodList = getAllProducts();

		return new ModelAndView("welcome", "cList", prodList);
	}
	
	
// this method has been extracted for reusability
	private ArrayList<ProductDto> getAllProducts() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(ProductDto.class);
		ArrayList<ProductDto> list = (ArrayList<ProductDto>) crit.list();
		tx.commit();
		session.close();
		return list;
	}

}
