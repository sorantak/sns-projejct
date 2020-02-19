package com.myspring.mysns.service;

import java.util.ArrayList;
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
import com.myspring.mysns.domain.FeedVO;
import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.repository.FeedDAO;
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

	@Autowired
	FeedVO feedVO;

	@Autowired
	FeedDAO feedDAO;

	public ResponseData savePost(PostVO postVO, String accesstoken) throws DataAccessException {

		TokenVO userByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("user by Token: " + userByToken);

		Long userId = userByToken.getUserId();
		postVO.setUserId(userId);
		logger.info("postVO: " + postVO);

		postDAO.savePost(postVO);

		Long id = postVO.getId();
		PostVO result = postDAO.findPostById(id);

		followVO.setFolloweeId(userId);
		List<FollowVO> findFollowers = followDAO.findFollowersByFollowee(userId);

		for (int j = 0; j < findFollowers.size(); j++) {
			feedVO.setUserId(findFollowers.get(j).getFollowerId());
			feedVO.setFolloweeId(userId);
			feedVO.setPostId(id);
			feedDAO.insertFeed(feedVO);
		}

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(result);

		return responseData;
	}

	@Override
	public ResponseData findAllPost(String accesstoken) throws DataAccessException {
		logger.info("call findAllPost() method in PostServiceImpl");
		List<PostAndUserVO> postList = postDAO.findAllPost();
		logger.info("postList.size: " + postList.size());
		logger.info("postList: " + postList.toString());
		if (accesstoken != null) {

			TokenVO userByToken = userDAO.viewUserByToken(accesstoken);
			logger.info("user by token: " + userByToken);

			Long userId = userByToken.getUserId();
			logger.info("user id: " + userId);

			List<FeedVO> followees = feedDAO.findFolloweeByUser(userId);
			logger.info("followees.size: " + followees.size());

			ArrayList<Long> postUser = new ArrayList<Long>(postList.size());
			ArrayList<Long> followeeList = new ArrayList<Long>(followees.size());

			for (int i = 0; i < postList.size(); i++) {
				postUser.add(postList.get(i).getUserId());

			}

			for (int j = 0; j < followees.size(); j++) {
				followeeList.add(followees.get(j).getFolloweeId());
			}

			int i = 0;
			for (Long postUserId : postUser) {
				boolean isFollow = false;
				for (Long followeeId : followeeList) {

					// if �� �۾��� id �� ���� �ȷο� ���� id ��
					if (postUserId.equals(followeeId)) {
						isFollow = true;
						break;
					} else {
						isFollow = false;
					}
				}
				postList.get(i).getUser().setIsFollow(isFollow);
				i++;
			}

			responseData.setCode(HttpStatus.OK);
			responseData.setMessage("SUCCESS");
			responseData.setData(postList);
			return responseData;
		} else {
			responseData.setCode(HttpStatus.OK);
			responseData.setMessage("SUCCESS");
			responseData.setData(postList);
			return responseData;

		}
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

		// MyBatis ������ ȣ�� �Ŀ� ���ϰ��� ������ 1, ���н� 0�� �ȴ�.
		int result = postDAO.deletePostById(id);
		logger.info("result: " + result);

		int feedResult = feedDAO.deleteFeedByPostId(id);
		logger.info("feedResult :" + feedResult);

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
