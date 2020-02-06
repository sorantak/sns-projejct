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

// REST API의 모든 datatype은 ResponseData 객체로 반환하도록 해야 함
@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	// 위랑 다르게 private 안 써준 이유?
	@Autowired
	UserVO userVO;
	
	@Autowired
	TokenVO tokenVO;
	
	@Autowired
	ResponseData responseData;

	// 1) 회원정보 조회 API
	// 전체 조회
	// method의 dataType이 ResponseData
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public ResponseData findAllUsersList() throws Exception {
		logger.info("call findAllUsersList() method in UserController");
		
		// userList 객체에 쿼리문 담아줌
		List<UserVO> userList = userService.findAllUsersList();
		
		// user 객체에 또 userList 객체 담아줌. 그냥 콘솔창에서 확인하려고
		for(UserVO user : userList) {
			System.out.println("first: " + user);
		}
		
		// responseData의 setter 실행
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userList);
		
		// responseData 출력
		System.out.println("전체 회원 조회 API: " + responseData);
		
		return responseData;
		
	}
	
	// id로 조회
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	// @RequestParam("id")를 long 형식의 인자로
	public ResponseData findUserById(@RequestParam("id") Long id) throws Exception {
		logger.info("call findUserById() method in UserController");
		
		// userVO 객체에 쿼리문 실행한 것 담아줌
		userVO = userService.findUserById(id);
		System.out.println("userVO: " + userVO);
		
		// responseData의 setter 실행
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userVO);
		
		// responseData 출력
		System.out.println("id별 회원 조회 API: " + responseData);
		
		return responseData;
		
	}
	
	// 2) 회원 가입 API
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	// @RequestBody 내용을 UserVO 형식으로 인자 설정
	public ResponseData saveUser(@RequestBody UserVO userVO) throws Exception {
		logger.info("call saveUser() method in UserController");
		
		// 1) 객체 없이 그냥 쿼리문 실행
		userService.saveUser(userVO);
		// 2) id 객체에 id를 저장
		Long id = userVO.getId();
		System.out.println("id: " + id);
		// 3) 그 id 포함한 data 가져온 것을 userVO 객체에 넣어줌
		userVO = userService.findUserById(id);
		// 4) responseData에 넣어줌
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(userVO);
		
		System.out.println("회원 가입 API: " + responseData);
		
		return responseData;
	}
	
	// 3) 회원 인증 API
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	// @RequestBody는 로그인창에서 입력한 username, password가 vo형식으로 메소드의 인자가 되게 함
	public ResponseData FindUserByToken(@RequestBody UserVO userVO) throws Exception {
		logger.info("call FindUserByToken() method in UserController");
		
		// 1) 로그인 쿼리문
		userVO = userService.logIn(userVO);
		System.out.println("user: " + userVO);
		
		// 2) 로그인한 회원id 조회. vo에 담긴 회원정보 중에 id만 가져옴
		Long id = userVO.getId();
		System.out.println("user id: " + id);
		
		// 3) token 생성
		StringBuffer token = RandomToken.createToken();
		System.out.println("token: " + token);
		
		// 4) token toString
		String tokenToString = token.toString();
		
		// 5) token 생성 쿼리문. id랑 token을 넣었으니 createdAt도 생길 것. tokenVO = 2) + 4)
		tokenVO.setUserId(id);
		tokenVO.setToken(tokenToString);
		System.out.println("tokenVO: " + tokenVO);
		userService.createToken(tokenVO);
		
		// 6) token으로 조회 쿼리문
		tokenVO = userService.viewUserByToken(tokenToString);
		System.out.println("tokenVO2: " + tokenVO);
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(tokenVO);
		
		System.out.println("회원 인증 API: " + responseData);
		
		return responseData;
	}

}