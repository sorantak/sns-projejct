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

	// ȸ������ ��ȸ API
	// id�� ��ȸ
	// @PathVariable annotation�� ����ϸ� url�ּҿ� {id}���� �־��ָ� ��. 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HashMap<String, Object> selectById(@PathVariable("id") Long id) throws DataAccessException {
		logger.info("call selectById method()");
		UserVO userVO = userService.selectById(id);
		System.out.println(userVO);
		// HashMap�� ���ϰ��� json �������� ��ȯ�ϵ��� ������ش�. put�� ����Ͽ� key�� value�� �߰�����.
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", HttpStatus.OK.value());
		map.put("message", "SUCCESS");
		map.put("data", userVO);
		return map;
	}

	// ��ü ��ȸ
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public HashMap<String, Object> selectAllUserList() throws DataAccessException {
		logger.info("call selectAllUserList method()");
		List<UserVO> userVO = userService.selectAllUserList();
		System.out.println(userVO);
		// HashMap�� ���ϰ��� json �������� ��ȯ�ϵ��� ������ش�. put�� ����Ͽ� key�� value�� �߰�����.
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", HttpStatus.OK.value());
		map.put("message", "SUCCESS");
		map.put("data", userVO);
		return map;
	}

	// ȸ������ API
	// ȸ������
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