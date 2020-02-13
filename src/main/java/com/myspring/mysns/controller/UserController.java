package com.myspring.mysns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.UserService;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public ResponseData findAllUsersList() throws Exception {
		logger.info("call findAllUsersList() method in UserController");
		
		return userService.findAllUsersList();
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseData findUserById(@RequestParam("id") Long id) throws Exception {
		logger.info("call findUserById() method in UserController");
		
		return userService.findUserById(id);
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseData saveUser(@RequestBody UserVO userVO) throws Exception {
		logger.info("call saveUser() method in UserController");
		
		return userService.saveUser(userVO);		
		
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseData FindUserByToken(@RequestBody UserVO userVO) throws Exception {
		logger.info("call FindUserByToken() method in UserController");
		ResponseData result = userService.FindUserByToken(userVO);
		logger.info("result: " + result);
		
		return result;
		
	}

}