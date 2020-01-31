package com.myspring.mysns.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.repository.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Inject
	private UserDAO dao;

	// DAO의 메소드: id로 조회
	@Override
	public UserVO selectById(Long id) throws DataAccessException {
		logger.info("call selectById() method in UserService");
		return dao.selectById(id);
	}

	// 전체 조회
	@Override
	public List<UserVO> selectAllUserList() throws DataAccessException {
		logger.info("call selecAllUserList() method in UserService");
		return dao.selectAllUserList();
	}

	@Override
	public int addUser(UserVO userVO) throws DataAccessException {
		logger.info("call addUser() method in UserService");
		return dao.insertUser(userVO);
	}
	
	@Override
	public UserVO login(UserVO userVO) throws DataAccessException {
		return dao.loginByUsername(userVO);
	}
	
	@Override
	public UserVO signupById(Long id) throws DataAccessException {
		return dao.signupById(id);
	}

}
