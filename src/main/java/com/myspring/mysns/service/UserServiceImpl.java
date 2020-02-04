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
	public List<UserVO> findAllUsersList() throws DataAccessException {
		logger.info("call findAllUsersList() method in UserService");
		return dao.findAllUsersList();
	}

	@Override
	public UserVO findUserById(Long id) throws DataAccessException {
		logger.info("call findUserById() method in UserService");
		return dao.findUserById(id);
	}

	@Override
	public int saveUser(UserVO userVO) throws DataAccessException {
		logger.info("call saveUser() method in UserService");
		return dao.saveUser(userVO);
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
	public TokenVO viewUserByToken(String token) throws DataAccessException {
		logger.info("call viewUserByToken() method in UserService");
		return dao.viewUserByToken(token);
	}

}
