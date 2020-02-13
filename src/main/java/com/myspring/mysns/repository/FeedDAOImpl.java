package com.myspring.mysns.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.mysns.domain.FeedVO;

@Repository
public class FeedDAOImpl implements FeedDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FollowDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	private static final String Namespace = "com.myspring.mysns.mappers.feed";

	@Override
	public int insertFeed(FeedVO feedVO) throws DataAccessException {
		logger.info("call insertFeed()");
		
		int result = sqlSession.insert(Namespace + ".insertFeed", feedVO);

		return result;
	}

	@Override
	public List<FeedVO> findFolloweeByUser(Long userId) throws DataAccessException {
		logger.info("call findFolloweeByUser()");
		
		List<FeedVO> result = sqlSession.selectList(Namespace + ".findFolloweeByUser", userId);

		return result;
	}
	

}
