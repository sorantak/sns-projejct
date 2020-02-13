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
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.service.PostService;

@RestController
//@RequestMapping("/post")
//@RequestMapping("/post/")
//@RequestMapping("/post/*")
//@RequestMapping("/post/**")
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseData savePost(@RequestBody PostVO postVO, @CookieValue(value = "accesstoken", required = false) String accesstoken)
			throws Exception {
		logger.info("call savePost() method in PostController");
		logger.info("@@accesstoken: " + accesstoken);
		return postService.savePost(postVO, accesstoken);
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseData findAllPost() throws Exception {
		logger.info("call findAllPost() method in PostController");

		return postService.findAllPost();
	}

	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseData findMyPost(@CookieValue(value = "accesstoken", required = false) String accesstoken) throws Exception {
		logger.info("call findMyPost() method in PostController");

		return postService.findMyPost(accesstoken);
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public ResponseData postDetailById(@PathVariable("postId") Long id) throws Exception {
		logger.info("call postDetailById() method in PostController");

		return postService.postDetailById(id);
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
	public ResponseData deletePost(@PathVariable("postId") Long id) throws Exception {
		logger.info("call deletePost() method in PostController");

		return postService.deletePostById(id);
	}

}
