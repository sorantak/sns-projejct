package com.myspring.mysns.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.UserVO;

public interface UserService {
	
	public UserVO selectById(Long id) throws DataAccessException;

	public List<UserVO> selectAllUserList() throws DataAccessException;
	
	public int addUser(UserVO userVO) throws DataAccessException;
	
	public UserVO login(UserVO userVO) throws DataAccessException;
	
	public UserVO signupById(Long id) throws DataAccessException;
	
}
