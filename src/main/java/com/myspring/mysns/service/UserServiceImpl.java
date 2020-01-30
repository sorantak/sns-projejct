package com.myspring.mysns.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.mysns.repository.UserDAO;
import com.myspring.mysns.repository.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	// DAO�� �޼ҵ�: id�� ��ȸ
	@Override
	public UserVO selectById(Long id) throws DataAccessException {
		return dao.selectById(id);
	}

	// ��ü ��ȸ
	@Override
	public List<UserVO> selectAllUserList() throws DataAccessException {
		return dao.selectAllUserList();
	}

	@Override
	public int addUser(UserVO userVO) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.insertUser(userVO);
		
	}

	// ȸ������
	//@Override
	//public int addUser(UserVO userVO) throws DataAccessException {
	//	return dao.insertUser(userVO);
	//}

	/*
	@Override
	public List listUsers() throws DataAccessException {
		List usersList = null;
		usersList = userDAO.selectAllUserList();
		return usersList;
	}

	@Override
	public int removeUser(int id) throws DataAccessException {
		return userDAO.deleteUser(id);
	}

	@Override
	public UserVO login(UserVO userVO) throws DataAccessException {
		return userDAO.loginById(userVO);
	}*/

}
