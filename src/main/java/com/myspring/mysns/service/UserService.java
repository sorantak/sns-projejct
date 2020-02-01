package com.myspring.mysns.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;

public interface UserService {

	public List<UserVO> viewAllUsersList() throws DataAccessException;
	
	public UserVO viewById(Long id) throws DataAccessException;
	
	public int signUp(UserVO userVO) throws DataAccessException;
	
	public int createToken(TokenVO tokenVO) throws DataAccessException;
	
	public TokenVO viewUserByToken(TokenVO tokenVO) throws DataAccessException;
	
}
