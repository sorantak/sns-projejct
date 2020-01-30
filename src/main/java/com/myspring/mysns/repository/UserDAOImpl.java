package com.myspring.mysns.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	// sqlSession�� ����� ������ ��ȯ
	// @Inject: �������, setter method(), ������, �Ϲ� method()�� ���� ����
	// java���� �����ϴ� annotation
	@Inject
	private SqlSession sqlSession; 
	
	private static final String Namespace = "com.myspring.mysns.mappers.userMapper";
	
	// id�� ��ȸ(������ urlâ�� id ���� �־��־�� ��)
	@Override
	public UserVO selectById(Long id) throws DataAccessException {
		//.selectById�� userMapper.xml���� ������� id��. 
		return sqlSession.selectOne(Namespace + ".selectById", id);
	}
	
	// ��ü ��ȸ
	@Override
	public List<UserVO> selectAllUserList() throws DataAccessException {
		return sqlSession.selectList(Namespace + ".selectAllUserList");
	}
	
	// ȸ������
		public int insertUser(UserVO userVO) throws DataAccessException {
			return sqlSession.insert(Namespace + ".insertUser", userVO);
		}
		//insert, delete, put(update)�� sqlSession method�� ��ȯŸ���� integer�̴�. �� ���� �����߳Ĵ� �ǹ�. 

	// �α���
	public UserVO loginByUsername(UserVO userVO) throws DataAccessException {
		return sqlSession.selectOne(Namespace + ".loginByUsername", userVO);
	}
	
}
