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

		// feed 테이블에 정보를 넣어줌(id는 postId, userId는 followeeId)
		followVO.setFolloweeId(userId);
		// Long followeeId = followVO.getFolloweeId();
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
		
		TokenVO userByToken = userDAO.viewUserByToken(accesstoken);
		logger.info("user by token: " + userByToken);

		Long userId = userByToken.getUserId();
		logger.info("user id: " + userId);

		List<FeedVO> followees = feedDAO.findFolloweeByUser(userId);
		logger.info("followees.size: " + followees.size());
		// list followeeList = feedVO.getFolloweeId();
		// 1. post list 길이 마큼 반복
		// 2. 하나의 pnuVO 에서 user id 가져오기

		ArrayList<Long> postUser = new ArrayList<Long>(postList.size());// for문 안에서 글쓴이 id
		ArrayList<Long> followeeList = new ArrayList<Long>(followees.size()); // for문 안에서 내가 팔로우중인 id		
		
		
		for (int i = 0; i < postList.size(); i++) {
//			logger.info( "for 1 . i = "+i);
			postUser.add(postList.get(i).getUserId());
			
		}

		for (int j = 0; j < followees.size(); j++) {
//			logger.info( "for 1 . J = "+j);
			followeeList.add(followees.get(j).getFolloweeId());

		}


		int i = 0;
		for (Long postUserId : postUser) {// userId 하나당 10
			boolean isFollow = false;
			for (Long followeeId : followeeList) { // followeeId 하나씩 4

				// if 로 글쓴이 id 와 내가 팔로우 중인 id 비교
				if (postUserId.equals(followeeId)) { // 둘이 맞는지 비교
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
