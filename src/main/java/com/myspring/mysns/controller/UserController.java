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

// REST API�� ��� datatype�� ResponseData ��ü�� ��ȯ�ϵ��� �ؾ� ��
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
	
	@Autowired
	RandomToken randomToken;

	// 1) ȸ������ ��ȸ API
	// ��ü ��ȸ
	// method�� dataType�� ResponseData
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public ResponseData viewAllUsersList() throws Exception {
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
		System.out.println("��ü ȸ�� ��ȸ API: " + responseData);
		
		return responseData;
		
	}
	
	// id�� ��ȸ
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	// @RequestParam("id")�� �Ƹ� ���������� ������ ��? Long id�� vo����
	public ResponseData viewUserById(@RequestParam("id") Long id) throws Exception {
		logger.info("call viewUserById() method in UserController");
		
		// userVO ��ü�� ������ �����
		userVO = userService.viewUserById(id);
		System.out.println("userVO: " + userVO);
		
		// responseData�� setter ����
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userVO);
		
		// responseData ���
		System.out.println("id�� ȸ�� ��ȸ API: " + responseData);
		
		return responseData;
		
	}
	
	// 2) ȸ�� ���� API
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	// @RequestBody�� ��� ������ ���ΰ�...?
	public ResponseData signUp(@RequestBody UserVO userVO) throws Exception {
		logger.info("call signUp() method in UserController");
		
		// 1) ��ü ���� �׳� ������ ����
		userService.signUp(userVO);
		// 2) id ��ü�� id�� ����
		Long id = userVO.getId();
		System.out.println("id: " + id);
		// 3) �� id ������ data ������ ���� userVO ��ü�� �־���
		userVO = userService.viewUserById(id);
		// 4) responseData�� �־���
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userVO);
		
		System.out.println("ȸ�� ���� API: " + responseData);
		
		return responseData;
	}
	
	// 3) ȸ�� ���� API
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	// @RequestBody�� �α���â���� �Է��� username, password�� vo�������� �޼ҵ��� ���ڰ� �ǰ� ��
	public ResponseData AuthorizeUser(@RequestBody UserVO userVO) throws Exception {
		logger.info("call AuthorizeUser() method in UserController");
		
		// 1) �α��� ������
		userVO = userService.logIn(userVO);
		System.out.println("user: " + userVO);
		
		// 2) �α����� ȸ��id ��ȸ. vo�� ��� ȸ������ �߿� id�� ������
		Long id = userVO.getId();
		System.out.println("user id: " + id);
		
		// 3) token ����
		StringBuffer token = randomToken.createToken();
		System.out.println("token: " + token);
		
		// 4) token toString
		String tokenToString = token.toString();
		
		// 5) token ���� ������. id�� token�� �־����� createdAt�� ���� ��. tokenVO = 2) + 4)
		tokenVO.setUserId(id);
		tokenVO.setToken(tokenToString);
		System.out.println("tokenVO: " + tokenVO);
		userService.createToken(tokenVO);
		
		// 6) token���� ��ȸ ������
		tokenVO = userService.viewUserByToken(tokenVO);
		System.out.println("tokenVO2: " + tokenVO);
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(tokenVO);
		
		System.out.println("ȸ�� ���� API: " + responseData);
		
		return responseData;
	}

}