package com.myspring.mysns.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.mysns.repository.UserVO;
import com.myspring.mysns.service.UserService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	public UserService service;

    @RequestMapping(value = "/mysns", method = RequestMethod.GET)
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
		
		return "login";
	}
	
}

/*
@RestController
@RequestMapping("/*")
public class HomeController {
	
	static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "
	}
	
}
*/