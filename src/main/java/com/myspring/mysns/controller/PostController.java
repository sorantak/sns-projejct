package com.myspring.mysns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.domain.UserVO;
import com.myspring.mysns.service.PostService;

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
	TokenVO tokenVO;

	@Autowired
	PostAndUserVO postAndUserVO;

	@Autowired
	UserVO userVO;

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseData savePost(@RequestBody PostVO postVO, @CookieValue(value = "accesstoken") String accesstoken)
			throws Exception {
		logger.info("call savePost() method in PostController");

		postService.savePost(postVO, accesstoken);

		return responseData;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseData findAllPost() throws Exception {
		logger.info("call findAllPost() method in PostController");

		postService.findAllPost();

		return responseData;
	}

	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseData findMyPost(@CookieValue(value = "accesstoken") String accesstoken) throws Exception {
		logger.info("call findMyPost() method in PostController");

		postService.findMyPost(accesstoken);

		return responseData;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public ResponseData postDetailById(@PathVariable("postId") Long id) throws Exception {
		logger.info("call postDetailById() method in PostController");

		postService.postDetailById(id);

		return responseData;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
	public ResponseData deletePost(@PathVariable("postId") Long id) throws Exception {
		logger.info("call deletePost() method in PostController");

		ResponseData deletedPost = postService.deletePostById(id);
		logger.info("call deletePostById() method in PostController");

		return deletedPost;
	}

}
