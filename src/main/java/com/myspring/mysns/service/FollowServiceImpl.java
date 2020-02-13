package com.myspring.mysns.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.FeedVO;
import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.repository.FeedDAO;
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
	
	@Autowired
	FeedVO feedVO;
	
	@Autowired
	FeedDAO feedDAO;
	
	@Autowired
	PostVO postVO;
	
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

		// followerId를 feedVO에 넣어줌
		feedVO.setUserId(followerId);
		logger.info("set followerId in feedVO: " + feedVO);
		
		// followerId로 나의 followees를 찾는 쿼리문 실행
		// 최소 1개 이상인 데이터이므로 List 형식
		List<FeedVO> followees = feedDAO.findFolloweeByUser(followerId); // 에러시작
		logger.info("followees 찾는 중: " + followees);
		
		PostAndUserVO[] followeePostList = new PostAndUserVO[followees.size()];
		for (int i = 0; i < followeePostList.length; i++) {
			PostAndUserVO postAndUserVO = new PostAndUserVO();
			
			Long postId = followees.get(i).getPostId();
			postVO = postDAO.findPostById(postId);
			postAndUserVO.setId(postId);
			Long userId = postVO.getUserId();
			postAndUserVO.setUserId(userId);
			String title = postVO.getTitle();
			postAndUserVO.setTitle(title);
			String content = postVO.getContent();
			postAndUserVO.setContent(content);
			String createdAt = postVO.getCreatedAt();
			postAndUserVO.setCreatedAt(createdAt);
			userVO = userDAO.findUserById(userId);
			postAndUserVO.setUser(userVO);
			followeePostList[i] = postAndUserVO;
		}
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(followeePostList);
		logger.info("내 followee들의 post: " + followeePostList);

		return responseData;

	}

}
