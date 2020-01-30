package com.myspring.mysns.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.repository.*;
import com.myspring.mysns.service.UserService;

@RestController
@RequestMapping("/*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// 회원정보 조회 API
	// id로 조회
	// @PathVariable annotation을 사용하면 url주소에 {id}값만 넣어주면 됨. 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HashMap<String, Object> selectById(@PathVariable("id") Long id) throws DataAccessException {
		logger.info("call selectById method()");
		UserVO userVO = userService.selectById(id);
		System.out.println(userVO);
		// HashMap은 리턴값을 json 형식으로 반환하도록 만들어준다. put을 사용하여 key와 value를 추가해줌.
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", HttpStatus.OK.value());
		map.put("message", "SUCCESS");
		map.put("data", userVO);
		return map;
	}

	// 전체 조회
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public HashMap<String, Object> selectAllUserList() throws DataAccessException {
		logger.info("call selectAllUserList method()");
		List<UserVO> userVO = userService.selectAllUserList();
		System.out.println(userVO);
		// HashMap은 리턴값을 json 형식으로 반환하도록 만들어준다. put을 사용하여 key와 value를 추가해줌.
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", HttpStatus.OK.value());
		map.put("message", "SUCCESS");
		map.put("data", userVO);
		return map;
	}

	// 회원가입 API
	// 회원가입
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public HashMap<?, ?> addUser(HttpServletRequest request) throws DataAccessException {
		logger.info("call addUser method()");
		String username = request.getParameter("username");
		System.out.println(username);
		return new HashMap<String, Object>();
	}
	
	@RequestMapping(value = "/signuptest", method=RequestMethod.POST)
	public HashMap<String, Object> signuptest(HttpServletRequest request) throws DataAccessException{
		System.out.println("request : " + request);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("id", "wonwoo");
		return hmap;
	}
}