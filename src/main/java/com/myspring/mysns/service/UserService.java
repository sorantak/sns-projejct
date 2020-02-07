package com.myspring.mysns.service;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.UserVO;

public interface UserService {

	public ResponseData findAllUsersList() throws DataAccessException;
	
	public ResponseData findUserById(Long id) throws DataAccessException;
	
	public ResponseData saveUser(UserVO userVO) throws DataAccessException;
	
	public ResponseData FindUserByToken(UserVO userVO) throws DataAccessException;
	
}
