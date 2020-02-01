package com.myspring.mysns.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.UserService;

@RestController
//@RequestMapping("/*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	// ���� �ٸ��� private �� ���� ����?
	@Autowired
	UserVO userVO;
	
	@Autowired
	TokenVO tokenVO;
	
	@Autowired
	ResponseData responseData;

	// ȸ������ ��ȸ API
	// ��ü ��ȸ
	// method�� dataType�� ResponseData
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public ResponseData viewAllUsersList() {
		logger.info("call viewAllUsersList() method in UserController");
		
		// userList ��ü�� ������ �����
		List<UserVO> userList = userService.viewAllUsersList();
		
		// user ��ü�� �� userList ��ü ����� (��?)
		for(UserVO user : userList) {
			System.out.println("first: " + user);
		}
		
		// responseData�� setter ����
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userList);
		
		// responseData ���
		System.out.println("Response Data: " + responseData);
		
		return responseData;
		
	}
	

/*
	// ȸ������ API
	// ȸ������
		// 1) ajax�� user(username, password)�� �޾ƿ�
		// 2) �� ���� userService�� addUser() method�� �����Ͽ�, DAO�� insertUser() method�� �����ϰ�
		// �ϰ�, DAO�� mapper�� insertUser sql���� �����ϰ� �ؼ� DB�� �����Ǿ�� ��.
		// 3) DB�� ����� �� ���� json �������� ��µǾ�� ��.
	@RequestMapping(value = "/user", method = RequestMethod.POST)
//	public ResponseEntity<String> addUser (@RequestBody UserVO vo) {
	public HashMap addUser (@RequestBody UserVO vo) {
		// ResponseEntity�� http �����ڵ带 �����ϴ� ������Ÿ��. ���������� ���� parameter(@Requestbody)�� vo �������� addUser() method�� ���ڷ� ����  		
		// ����ó���� �����Ͽ� �õ��� �� vo ������ parameter�� �������� �����ϵ��� ��. ���� �Ǵ� ���� �� http �����ڵ� ��ȯ
		try {
			logger.info("call addUser() method in UserController");
			logger.info(vo.toString());
			System.out.println(vo);
			userService.addUser(vo);
			
			UserVO selectIdVO = userService.signupById(vo.getId());
			
			HashMap vo_map = new HashMap();
			vo_map.put("id", selectIdVO.getId());
			vo_map.put("username", selectIdVO.getUsername());
			vo_map.put("created_at", selectIdVO.getCreated_at());
			
			HashMap r_map = new HashMap();
			r_map.put("code", HttpStatus.OK.value());
			r_map.put("message", "SUCCESS");
			r_map.put("data", vo_map);
			
			return r_map;
		} catch(Exception e) {
			e.printStackTrace();
			return new HashMap();
		}
	}
	
	// ȸ������ API
	// �α���(ȸ������)
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<String> login (@RequestBody UserVO vo) {
		// ResponseEntity�� http �����ڵ带 �����ϴ� ������Ÿ��. ���������� ���� parameter(@Requestbody)�� vo �������� addUser() method�� ���ڷ� ����  		
		// ����ó���� �����Ͽ� �õ��� �� vo ������ parameter�� �������� �����ϵ��� ��. ���� �Ǵ� ���� �� http �����ڵ� ��ȯ
		try {
			logger.info("call login() method in UserController");
			logger.info(vo.toString());
			userService.login(vo);
			return new ResponseEntity<String>("Add on DB Success",HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	} */

}