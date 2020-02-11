package com.myspring.mysns.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.repository.FollowDAO;
import com.myspring.mysns.repository.UserDAO;

@Service
public class FollowServiceImpl implements FollowService{

	private static final Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);

	@Autowired
	ResponseData responseData;
	
	@Autowired
	FollowDAO followDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserVO userVO;
	
	@Autowired
	TokenVO tokenVO;
	
	@Autowired
	FollowVO followVO;
	
	@Override
	public ResponseData followUser(FollowVO followeeId, String accesstoken) throws DataAccessException {
		logger.info("call followUser()");
		
		TokenVO followerByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("follower by token: " + followerByToken);
		
		Long followerId = followerByToken.getUserId();
		logger.info("follower id: " + followerId);
		
		followeeId.setFollowerId(followerId);
		logger.info(followeeId.toString());
		followDAO.followUser(followeeId);
		logger.info(followVO.toString());
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("OK");
		responseData.setData("SUCCESS");
		return responseData;
	}

	@Override
	public ResponseData unfollowUser(FollowVO followeeId, String accesstoken) {
		logger.info("call unfollowUser()");
		
		TokenVO followerByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("follower by token: " + followerByToken);
		
		Long followerId = followerByToken.getUserId();
		logger.info("follower id: " + followerId);
		
		followeeId.setFollowerId(followerId);
		logger.info(followeeId.toString());
		followDAO.unfollowUser(followeeId);
		logger.info(followVO.toString());
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("OK");
		responseData.setData("SUCCESS");
		return responseData;
	}

}
