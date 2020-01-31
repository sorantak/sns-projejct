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

import com.myspring.mysns.domain.UserVO;
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
		logger.info("call selectById() method in UserController");
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
		logger.info("call selectAllUserList() method in UserController");
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
		// 1) ajax의 user(username, password)를 받아옴
		// 2) 그 값을 userService의 addUser() method를 실행하여, DAO의 insertUser() method를 실행하게
		// 하고, DAO는 mapper의 insertUser sql문을 실행하게 해서 DB에 연동되어야 함.
		// 3) DB에 저장된 그 값만 json 형식으로 출력되어야 함.
	@RequestMapping(value = "/user", method = RequestMethod.POST)
//	public ResponseEntity<String> addUser (@RequestBody UserVO vo) {
	public HashMap addUser (@RequestBody UserVO vo) {
		// ResponseEntity는 http 상태코드를 전송하는 데이터타입. 브라우저에서 받은 parameter(@Requestbody)를 vo 형식으로 addUser() method의 인자로 설정  		
		// 예외처리를 설정하여 시도할 때 vo 형식의 parameter로 쿼리문을 실행하도록 함. 성공 또는 실패 시 http 상태코드 반환
		try {
			logger.info("call addUser() method in UserController");
			logger.info(vo.toString());
			System.out.println(vo);
			userService.addUser(vo);
			
//			UserVO selectUsernameVO = userService.selectByUsername(vo.getUsername());
			
			HashMap vo_map = new HashMap();
//			vo_map.put("id", selectUsernameVO.getId()); 
//			vo_map.put("username", selectUsernameVO.getUsername());
//			vo_map.put("created_at", selectUsernameVO.getCreated_at());
			
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
	
	// 회원인증 API
	// 로그인(회원인증)
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<String> login (@RequestBody UserVO vo) {
		// ResponseEntity는 http 상태코드를 전송하는 데이터타입. 브라우저에서 받은 parameter(@Requestbody)를 vo 형식으로 addUser() method의 인자로 설정  		
		// 예외처리를 설정하여 시도할 때 vo 형식의 parameter로 쿼리문을 실행하도록 함. 성공 또는 실패 시 http 상태코드 반환
		try {
			logger.info("call login() method in UserController");
			logger.info(vo.toString());
			userService.login(vo);
			return new ResponseEntity<String>("Add on DB Success",HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}