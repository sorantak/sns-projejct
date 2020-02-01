package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;

public interface UserDAO {
	
	// ��ü ��ȸ
	public List<UserVO> viewAllUsersList() throws DataAccessException;
	
	// id�� ��ȸ
	public UserVO viewUserById(Long id) throws DataAccessException;
	
	// ȸ������
	public int signUp(UserVO userVO) throws DataAccessException ;
	
	// �α���
	public UserVO logInByUser(UserVO userVO) throws DataAccessException;

	// ��ū ����
	public int createToken(TokenVO tokenVO) throws DataAccessException;
	
	// ��ū���� ��ȸ
	public TokenVO viewUserByToken(TokenVO token) throws DataAccessException;

}

