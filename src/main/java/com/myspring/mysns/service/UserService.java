package com.myspring.mysns.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;

public interface UserService {

	public List<UserVO> findAllUsersList() throws DataAccessException;
	
	public UserVO findUserById(Long id) throws DataAccessException;
	
	public int saveUser(UserVO userVO) throws DataAccessException;
	
	public UserVO logIn(UserVO userVO) throws DataAccessException;
	
	public int createToken(TokenVO tokenVO) throws DataAccessException;
	
	public TokenVO viewUserByToken(String token) throws DataAccessException;
	
}
