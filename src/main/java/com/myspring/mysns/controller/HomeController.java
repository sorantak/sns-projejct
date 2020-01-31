package com.myspring.mysns.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.UserService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	public UserService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws Exception{
 
        logger.info("home");
        
        List<UserVO> userList = service.selectAllUserList();
        
        model.addAttribute("userList", userList);

        return "home";
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "signup";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private ModelAndView login(HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private ModelAndView index(HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
}