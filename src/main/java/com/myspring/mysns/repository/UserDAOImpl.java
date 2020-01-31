package com.myspring.mysns.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.UserService;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	// sqlSession�� ����� ������ ��ȯ
	// @Inject: �������, setter method(), ������, �Ϲ� method()�� ���� ����
	// java���� �����ϴ� annotation
	@Inject
	private SqlSession sqlSession; 
	
	private static final String Namespace = "com.myspring.mysns.mappers.userMapper";
	
	// id�� ��ȸ(������ urlâ�� id ���� �־��־�� ��)
	@Override
	public UserVO selectById(Long id) throws DataAccessException {
		logger.info("call selectById() method in UserDAO");
		//.selectById�� userMapper.xml���� ������� id��. 
		return sqlSession.selectOne(Namespace + ".selectById", id);
	}
	
	// ��ü ��ȸ
	@Override
	public List<UserVO> selectAllUserList() throws DataAccessException {
		logger.info("call selectAllUserList() method in UserDAO");
		return sqlSession.selectList(Namespace + ".selectAllUserList");
	}
	
	// ȸ������
	public int insertUser(UserVO userVO) throws DataAccessException {
		logger.info("call insertUser() method in UserDAO");
		return sqlSession.insert(Namespace + ".insertUser", userVO);
	}
		//insert, delete, put(update)�� sqlSession method�� ��ȯŸ���� integer�̴�. �� ���� �����߳Ĵ� �ǹ�. 

	// �α���
	public UserVO loginByUsername(UserVO userVO) throws DataAccessException {
		logger.info("call loginByUsername() method in UserDAO");
		return sqlSession.selectOne(Namespace + ".loginByUsername", userVO);
	}
	
	// username���� ��ȸ
	public UserVO signupById(Long id) throws DataAccessException {//"username"
		logger.info("call signupById() method in UserDAO");
		return sqlSession.selectOne(Namespace + ".signupById", id);
	}
}
