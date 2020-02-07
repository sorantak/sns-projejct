package com.myspring.mysns.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.UserService;
import com.myspring.mysns.util.RandomToken;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	UserVO userVO;
	
	@Autowired
	TokenVO tokenVO;
	
	@Autowired
	ResponseData responseData;

	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public ResponseData findAllUsersList() throws Exception {
		logger.info("call findAllUsersList() method in UserController");
		
		List<UserVO> userList = userService.findAllUsersList();
		
		for(UserVO user : userList) {
			System.out.println("first: " + user);
		}
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userList);
		
		System.out.println("전체 회원 조회 API: " + responseData);
		
		return responseData;
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseData findUserById(@RequestParam("id") Long id) throws Exception {
		logger.info("call findUserById() method in UserController");
		
		userVO = userService.findUserById(id);
		System.out.println("userVO: " + userVO);
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userVO);
		
		System.out.println("id별 회원 조회 API: " + responseData);
		
		return responseData;
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseData saveUser(@RequestBody UserVO userVO) throws Exception {
		logger.info("call saveUser() method in UserController");
		
		userService.saveUser(userVO);
		Long id = userVO.getId();
		System.out.println("id: " + id);
		userVO = userService.findUserById(id);
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userVO);
		
		System.out.println("회원 가입 API: " + responseData);
		
		return responseData;
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseData FindUserByToken(@RequestBody UserVO userVO) throws Exception {
		logger.info("call FindUserByToken() method in UserController");
		
		userVO = userService.logIn(userVO);
		System.out.println("user: " + userVO);
		
		Long id = userVO.getId();
		System.out.println("user id: " + id);
		
		StringBuffer token = RandomToken.createToken();
		System.out.println("token: " + token);
		
		String tokenToString = token.toString();
		
		tokenVO.setUserId(id);
		tokenVO.setToken(tokenToString);
		System.out.println("tokenVO: " + tokenVO);
		userService.createToken(tokenVO);
		
		tokenVO = userService.viewUserByToken(tokenToString);
		System.out.println("tokenVO2: " + tokenVO);
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(tokenVO);
		
		System.out.println("회원 인증 API: " + responseData);
		
		return responseData;
	}

}