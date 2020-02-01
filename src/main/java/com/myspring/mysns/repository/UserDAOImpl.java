package com.myspring.mysns.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	// sqlSession�� ����� ������ ��ȯ
	// @Inject: �������, setter method(), ������, �Ϲ� method()�� ���� ����
	// java���� �����ϴ� annotation
	@Autowired
	private SqlSession sqlSession; 
	
	private static final String Namespace = "com.myspring.mysns.mappers.userMapper";
	
	// ��ü ��ȸ
	@Override
	public List<UserVO> viewAllUsersList() throws DataAccessException {
		logger.info("call viewAllUsersList() method in UserDAO");
		return sqlSession.selectList(Namespace + ".viewAllUsersList");
	}
	
	// id�� ��ȸ(������ urlâ�� id ���� �־��־�� ��)
	@Override
	public UserVO viewById(Long id) throws DataAccessException {
		logger.info("call viewById() method in UserDAO");
		//.viewById�� userMapper.xml���� ������� id��. 
		return sqlSession.selectOne(Namespace + ".viewById", id);
	}
	
	// ȸ������
	@Override
	public int signUp(UserVO userVO) throws DataAccessException {
		logger.info("call signUp() method in UserDAO");
		return sqlSession.insert(Namespace + ".signUp", userVO);
	}
	
	// �α���
	@Override
	public UserVO loginByUser(UserVO userVO) throws DataAccessException {
		logger.info("call loginByUser() method in UserDAO");
		return sqlSession.selectOne(Namespace + ".loginByUser", userVO);
	}
	
	// ��ū ����
	@Override
	public int createToken(TokenVO tokenVO) throws DataAccessException {
		logger.info("call createToken() method in UserDAO");
		return sqlSession.insert(Namespace + ".createToken", tokenVO);
	}
	
	// ��ū���� ��ȸ
	@Override
	public TokenVO viewUserByToken(TokenVO token) throws DataAccessException {
		logger.info("call viewUserByToken() method in UserDAO");
		return sqlSession.selectOne(Namespace + ".viewUserByToken", token);
	}
	
}
