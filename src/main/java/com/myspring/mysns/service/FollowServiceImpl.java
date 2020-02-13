package com.myspring.mysns.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.repository.FollowDAO;
import com.myspring.mysns.repository.PostDAO;
import com.myspring.mysns.repository.UserDAO;

@Service
public class FollowServiceImpl implements FollowService {

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

	@Autowired
	PostAndUserVO postAndUserVO;

	@Autowired
	PostDAO postDAO;

	@Override
	public ResponseData followUser(FollowVO followeeIdInVO, String accesstoken) throws DataAccessException {
		logger.info("call followUser()");

		try {
			TokenVO followerByToken = userDAO.viewUserByToken(accesstoken);
			logger.info("follower by token: " + followerByToken);

			Long followerId = followerByToken.getUserId();
			logger.info("follower id: " + followerId);

			followeeIdInVO.setFollowerId(followerId);
			logger.info(followeeIdInVO.toString());
			followDAO.followUser(followeeIdInVO);

			responseData.setCode(HttpStatus.OK);
			responseData.setMessage("OK");
			responseData.setData("SUCCESS");
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			responseData.setMessage("INTERNAL_SERVER_ERROR");
			responseData.setData("FAIL");
			return responseData;
		}

	}

	@Override
	public ResponseData unfollowUser(FollowVO followeeId, String accesstoken) throws DataAccessException {
		logger.info("call unfollowUser()");

		try {
			TokenVO followerByToken = userDAO.viewUserByToken(accesstoken);
			logger.info("follower by token: " + followerByToken);

			Long followerId = followerByToken.getUserId();
			logger.info("follower id: " + followerId);

			followeeId.setFollowerId(followerId);
			logger.info(followeeId.toString());
			followDAO.unfollowUser(followeeId);

			responseData.setCode(HttpStatus.OK);
			responseData.setMessage("OK");
			responseData.setData("SUCCESS");
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			responseData.setMessage("INTERNAL_SERVER_ERROR");
			responseData.setData("FAIL");
			return responseData;
		}

	}

	@Override
	public ResponseData viewMyFeedList(String accesstoken) throws DataAccessException {
		logger.info("call viewMyFeedList()");

		TokenVO followerByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("follower by token: " + followerByToken);

		Long followerId = followerByToken.getUserId();
		logger.info("follower id: " + followerId);
		// 여기까지는 accesstoken으로 followerId를 가져오는 것으로 위와 같음

		// dao에 list로 반환해야 함
		// createdat별로 desc 정렬

		// followerid로 followeeid를 가져와야 함
		followVO.setFollowerId(followerId);
		Long followeeId = followVO.getFolloweeId();

		// followeeid로 postid를 가져옴
		postAndUserVO.setUserId(followeeId);
		logger.info("postAndUserVO: " + postAndUserVO);
		List<PostAndUserVO> result = postDAO.findMyPost(followeeId);

		responseData.setData(result);
		logger.info("result: " + result);

		/*
		 * feedVO.setUserId(userId); List<FeedVO> feedList =
		 * followDAO.viewFeedListByUser(userId);
		 */

		return responseData;
	}

}
