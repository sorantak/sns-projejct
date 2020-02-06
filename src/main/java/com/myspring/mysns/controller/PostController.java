package com.myspring.mysns.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.PostService;
import com.myspring.mysns.service.UserService;

// *Postman으로 실습*
@RestController
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	ResponseData responseData;
	@Autowired
	PostVO postVO;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	TokenVO tokenVO;
	@Autowired
	PostAndUserVO postAndUserVO;
	@Autowired
	UserVO userVO;

	// 글 저장 API
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseData savePost(@RequestBody PostVO postVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("call savePost() method in PostController");

		String tokenCookie = request.getHeader("accesstoken");
		logger.info("accesstoken: " + tokenCookie);

		tokenVO.setToken(tokenCookie);

		TokenVO tokenVO = userService.viewUserByToken(tokenCookie);
		logger.info("tokenVO: " + tokenVO);

		Long userId = tokenVO.getUserId();
		postVO.setUserId(userId);
		logger.info("postVO: " + postVO);

		postService.savePost(postVO);

		Long id = postVO.getId();
		PostVO result = postService.findPostById(id);

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(result);

		return responseData;
	}

	// 전체 글 리스트 조회 API
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseData findAllPost() throws Exception {
		logger.info("call findAllPost() method in PostController");

		List<PostAndUserVO> postList = postService.findAllPost();
		for (PostAndUserVO pau : postList) {
			logger.info("postList: " + pau);
		}

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(postList);

		logger.info("전체 글 리스트 조회 API: " + responseData);

		return responseData;
	}

	// 내가 쓴 글 리스트 조회 API
	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseData findMyPost(@RequestParam("id") Long id) throws Exception {
		logger.info("call findMyPost() method in PostController");

		userVO = userService.findUserById(id);
		logger.info("userVO: " + userVO);

		Long userId = userVO.getId();
		List<PostAndUserVO> myPostList = postService.findMyPost(userId);
		logger.info("myPostList: " + myPostList);

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(myPostList);
		return responseData;
	}

	// 글 상세 조회 API
	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public ResponseData postDetailById(@PathVariable("postId") Long id) throws Exception {
		logger.info("call postDetailById() method in PostController");

		PostAndUserVO postDetail = postService.postDetailById(id);
		logger.info("vo: " + postDetail.toString());

		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(postDetail);

		return responseData;
	}

	// 글 삭제 API
	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
	public ResponseData deletePost(@PathVariable("postId") Long id) throws Exception {
		logger.info("call deletePost() method in PostController");

		ResponseData deletedPost = postService.deletePostById(id);
		logger.info("call deletePostById() method in PostController");

		return deletedPost;
	}

}
