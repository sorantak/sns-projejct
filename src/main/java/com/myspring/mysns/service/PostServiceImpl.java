package com.myspring.mysns.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.repository.FollowDAO;
import com.myspring.mysns.repository.PostDAO;
import com.myspring.mysns.repository.UserDAO;

@Service
public class PostServiceImpl implements PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostDAO postDAO;

	@Autowired
	ResponseData responseData;

	@Autowired
	TokenVO tokenVO;

	@Autowired
	UserService userService;

	@Autowired
	PostVO postVO;

	@Autowired
	UserVO userVO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	FollowVO followVO;
	
	@Autowired
	FollowDAO followDAO;

	public ResponseData savePost(PostVO postVO, String accesstoken) throws DataAccessException {

		TokenVO userByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("user by Token: " + userByToken);

		Long userId = userByToken.getUserId();
		postVO.setUserId(userId);
		logger.info("postVO: " + postVO);

		postDAO.savePost(postVO);

		Long id = postVO.getId();
		PostVO result = postDAO.findPostById(id);
		/*
		// feed 테이블에 정보를 넣어줌(id는 postId, userId는 followeeId)
		followVO.setFolloweeId(userId);
		List<FollowVO> findFollower = followDAO.findFollowerByFollowee; // findFollowerByFollowee = SELECT * FROM follow WHERE followeeId=post의 user;
		
		for (int j = 0; j < findFollower.length; j++) {
			feedVO.setUserId(findFollower.getFollowerId());
			feedVO.setFolloweeId(userId);
			feedVO.setPostId(id);
			feedDAO.insertFeed(feedVO);
		}*/
	
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(result);

		return responseData;
	}

	@Override
	public ResponseData findAllPost() throws DataAccessException {
		logger.info("call findAllPost() method in PostServiceImpl");
		List<PostAndUserVO> postList = postDAO.findAllPost();
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(postList);

		return responseData;
	}

	@Override
	public ResponseData findMyPost(String accesstoken) throws DataAccessException {
		logger.info("call findMyPost() method in PostServiceImpl");

		TokenVO userByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("user by Token: " + userByToken);

		Long userId = userByToken.getUserId();
		logger.info("user id: " + userId);

		List<PostAndUserVO> myPostList = postDAO.findMyPost(userId);
		logger.info("myPostList: " + myPostList);

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(myPostList);

		return responseData;
	}

	@Override
	public ResponseData postDetailById(Long id) throws DataAccessException {
		logger.info("call postDetailById() method in PostServiceImpl");

		PostAndUserVO postDetail = postDAO.postDetailById(id);

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(postDetail);

		return responseData;
	}
	
	@Override
	public ResponseData deletePostById(Long id) throws DataAccessException {
		logger.info("call deletePostById() method in PostServiceImpl");

		// MyBatis 쿼리문 호출 후에 리턴값은 성공시 1, 실패시 0이 된다.
		int result = postDAO.deletePostById(id);
		logger.info("result: " + result);

		if (result == 1) {
			responseData.setCode(HttpStatus.OK);
			responseData.setMessage("SUCCESS");
			responseData.setData(null);
		} else {
			responseData.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			responseData.setMessage("ERROR");
			responseData.setData(null);
		}

		return responseData;
	}

}
