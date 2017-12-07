package com.gc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dao.CurrentScoreDao;
import com.gc.dao.CurrentScoreDaoImpl;
import com.gc.dto.CurrentScoreDto;

@Controller
public class HomeController {
	
	@RequestMapping({"/", "index"})
	public ModelAndView homepage() {
		CurrentScoreDto dto = new CurrentScoreDto(); 
	 CurrentScoreDao dao = new CurrentScoreDaoImpl(); 
		
		dao.addcurrentScore( 1, 2);
		return new ModelAndView("index","", "");
	}

	@RequestMapping("preferences")
	public ModelAndView preferences() {
		return new ModelAndView("preferences","", "");
	}
	
	@RequestMapping("voting")
	public ModelAndView voting() {
		return new ModelAndView("voting","", "");
	}
	
	@RequestMapping("finalResults")
	public ModelAndView finalResult() {
		return new ModelAndView("finalResults","", "");
	}
}
