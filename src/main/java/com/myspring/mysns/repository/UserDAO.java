package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.UserVO;

public interface UserDAO {
	
	public UserVO selectById(Long id) throws DataAccessException;
	
	public List<UserVO> selectAllUserList() throws DataAccessException;
	
	public int insertUser(UserVO userVO) throws DataAccessException ;
	
	public UserVO loginByUsername(UserVO userVO) throws DataAccessException;

	public UserVO signupById(Long id) throws DataAccessException;

}

