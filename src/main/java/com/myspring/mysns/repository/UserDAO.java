package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface UserDAO {
	
	public UserVO selectById(Long id) throws DataAccessException;
	
	public List<UserVO> selectAllUserList() throws DataAccessException;
	
	public int insertUser(UserVO userVO) throws DataAccessException ;
	
	/*
	public int deleteUser(int id) throws DataAccessException;
	public UserVO loginById(UserVO userVO) throws DataAccessException;
	*/

}

