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
	
	// sqlSession에 저장된 쿼리문 반환
	// @Inject: 멤버변수, setter method(), 생성자, 일반 method()에 적용 가능
	// java에서 지원하는 annotation
	@Inject
	private SqlSession sqlSession; 
	
	private static final String Namespace = "com.myspring.mysns.mappers.userMapper";
	
	// id로 조회(브라우저 url창에 id 값을 넣어주어야 함)
	@Override
	public UserVO selectById(Long id) throws DataAccessException {
		logger.info("call selectById() method in UserDAO");
		//.selectById는 userMapper.xml에서 만들어준 id임. 
		return sqlSession.selectOne(Namespace + ".selectById", id);
	}
	
	// 전체 조회
	@Override
	public List<UserVO> selectAllUserList() throws DataAccessException {
		logger.info("call selectAllUserList() method in UserDAO");
		return sqlSession.selectList(Namespace + ".selectAllUserList");
	}
	
	// 회원가입
	public int insertUser(UserVO userVO) throws DataAccessException {
		logger.info("call insertUser() method in UserDAO");
		return sqlSession.insert(Namespace + ".insertUser", userVO);
	}
		//insert, delete, put(update)는 sqlSession method의 반환타입이 integer이다. 몇 행을 수행했냐는 의미. 

	// 로그인
	public UserVO loginByUsername(UserVO userVO) throws DataAccessException {
		logger.info("call loginByUsername() method in UserDAO");
		return sqlSession.selectOne(Namespace + ".loginByUsername", userVO);
	}
	
	// username으로 조회
	public UserVO signupById(Long id) throws DataAccessException {//"username"
		logger.info("call signupById() method in UserDAO");
		return sqlSession.selectOne(Namespace + ".signupById", id);
	}
}
