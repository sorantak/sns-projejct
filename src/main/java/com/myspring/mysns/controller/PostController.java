package com.myspring.mysns.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.TokenVO;
import com.myspring.mysns.service.PostService;
import com.myspring.mysns.service.UserService;

// *Postman���� �ǽ�*
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
	
	// �� ���� API
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseData savePost(@RequestBody PostVO postVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("call savePost() method in PostController");
		
		// 1) �������κ��� user�� token�� ��� ��Ű�� �޾ƿ´�
		String tokenCookie = request.getHeader("accesstoken");
		logger.info("accesstoken: " + tokenCookie);
		// 2) �ܼ� ���ڿ� tokenCookie�� setter�� vo�� �־��ش�
		tokenVO.setToken(tokenCookie);
		// 3) tokenCookie�� ������ ������ ���� vo Ÿ������ ��ü�� �־��ش�
		TokenVO tokenVO = userService.viewUserByToken(tokenCookie);
		logger.info("tokenVO: " + tokenVO);
		// 4) vo�� ����ִ� userId�� Long Ÿ������ �����´�
		Long userId = tokenVO.getUserId();
		// 5) userId�� postVO�� setter�� userId�� �־��ش�
		postVO.setUserId(userId);
		logger.info("postVO: " + postVO);
		// 6) userId�� @RequestBody�� ���� title, content�� �������� �����Ѵ�
		postService.savePost(postVO);
		// 7) ������ ���������� id�� �����´�
		Long id = postVO.getId();
		// 8) id�� �������� �����Ѵ�
		PostVO result = postService.findPostById(id);
		// 9) responseData�� �־��ְ� ��ȯ
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(result);
		
		return responseData;
	}
	
	// ��ü �� ����Ʈ ��ȸ API
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseData findAllPost() throws Exception {
		logger.info("call findAllPost() method in PostController");
		
		List<PostAndUserVO> postList = postService.findAllPost();
		for(PostAndUserVO pau : postList) {
			logger.info("postList: " + postList);
		}
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(postList);
		
		logger.info("��ü �� ����Ʈ ��ȸ API: " + responseData);
		
		return responseData;
	}
	
	// ���� �� �� ����Ʈ ��ȸ API
	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseData findMyPost() throws Exception {
		logger.info("call findMyPost() method in PostController");
		return responseData;
	}

}
