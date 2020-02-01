package com.myspring.mysns.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.repository.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO dao;
	
	@Override
	public List<UserVO> viewAllUsersList() throws DataAccessException {
		logger.info("call viewAllUsersList() method in UserService");
		return dao.viewAllUsersList();
	}

	@Override
	public UserVO viewUserById(Long id) throws DataAccessException {
		logger.info("call viewUserById() method in UserService");
		return dao.viewUserById(id);
	}

	@Override
	public int signUp(UserVO userVO) throws DataAccessException {
		logger.info("call signUp() method in UserService");
		return dao.signUp(userVO);
	}
	
	@Override
	public UserVO logIn(UserVO userVO) throws DataAccessException {
		logger.info("call logIn() method in UserService");
		return dao.logInByUser(userVO);
	}

	@Override
	public int createToken(TokenVO tokenVO) throws DataAccessException {
		logger.info("call createToken() method in UserService");
		return dao.createToken(tokenVO);
	}

	@Override
	public TokenVO viewUserByToken(TokenVO tokenVO) throws DataAccessException {
		logger.info("call viewUserByToken() method in UserService");
		return dao.viewUserByToken(tokenVO);
	}

}
