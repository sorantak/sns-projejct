package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;

public interface UserDAO {
	
	// 전체 조회
	public List<UserVO> findAllUsersList() throws DataAccessException;
	
	// id로 조회
	public UserVO findUserById(Long id) throws DataAccessException;
	
	// 회원가입
	public int saveUser(UserVO userVO) throws DataAccessException ;
	
	// 로그인
	public UserVO logInByUser(UserVO userVO) throws DataAccessException;

	// 토큰 생성
	public int createToken(TokenVO tokenVO) throws DataAccessException;
	
	// 토큰으로 조회
	public TokenVO viewUserByToken(String token) throws DataAccessException;

}

