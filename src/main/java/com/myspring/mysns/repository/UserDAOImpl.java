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
	
	// sqlSession에 저장된 쿼리문 반환
	@Autowired
	private SqlSession sqlSession; 
	
	private static final String Namespace = "com.myspring.mysns.mappers.mappers";
	
	// 전체 조회
	@Override
	public List<UserVO> findAllUsersList() throws DataAccessException {
		logger.info("call findAllUsersList() method in UserDAO");
		List<UserVO> resultList = sqlSession.selectList(Namespace + ".findAllUsersList");
		return resultList;
	}
	
	// id로 조회(브라우저 url창에 id 값을 넣어주어야 함)
	@Override
	public UserVO findUserById(Long id) throws DataAccessException {
		logger.info("call findUserById() method in UserDAO");
		//.viewById는 userMapper.xml에서 만들어준 id임. 
		UserVO result = sqlSession.selectOne(Namespace + ".findUserById", id);
		return result;
	}
	
	// 회원가입
	@Override
	public int saveUser(UserVO userVO) throws DataAccessException {
		logger.info("call saveUser() method in UserDAO");
		int result = sqlSession.insert(Namespace + ".saveUser", userVO);
		return result;
	}
	
	// 로그인
	@Override
	public UserVO logInByUser(UserVO userVO) throws DataAccessException {
		logger.info("call loginByUser() method in UserDAO");
		UserVO result = sqlSession.selectOne(Namespace + ".logInByUser", userVO);
		return result;
	}
	
	// 토큰 생성
	@Override
	public int createToken(TokenVO tokenVO) throws DataAccessException {
		logger.info("call createToken() method in UserDAO");
		int result = sqlSession.insert(Namespace + ".createToken", tokenVO);
		return result;
	}
	
	// 토큰으로 조회
	@Override
	public TokenVO viewUserByToken(String token) throws DataAccessException {
		logger.info("call viewUserByToken() method in UserDAO");
		TokenVO result = sqlSession.selectOne(Namespace + ".viewUserByToken", token);
		return result;
	}
	
}
