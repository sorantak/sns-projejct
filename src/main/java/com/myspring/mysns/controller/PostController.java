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
	
	// 글 저장 API
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseData savePost(@RequestBody PostVO postVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("call savePost() method in PostController");
		
		// 1) 브라우저로부터 user의 token이 담긴 쿠키를 받아온다
		String tokenCookie = request.getHeader("accesstoken");
		logger.info("accesstoken: " + tokenCookie);
		// 2) 단순 문자열 tokenCookie를 setter로 vo에 넣어준다
		tokenVO.setToken(tokenCookie);
		// 3) tokenCookie로 쿼리문 실행한 것을 vo 타입으로 객체에 넣어준다
		TokenVO tokenVO = userService.viewUserByToken(tokenCookie);
		logger.info("tokenVO: " + tokenVO);
		// 4) vo에 들어있는 userId를 Long 타입으로 가져온다
		Long userId = tokenVO.getUserId();
		// 5) userId를 postVO에 setter로 userId에 넣어준다
		postVO.setUserId(userId);
		logger.info("postVO: " + postVO);
		// 6) userId와 @RequestBody로 받은 title, content로 쿼리문을 실행한다
		postService.savePost(postVO);
		// 7) 실행한 쿼리문에서 id를 가져온다
		Long id = postVO.getId();
		// 8) id로 쿼리문을 실행한다
		PostVO result = postService.findPostById(id);
		// 9) responseData에 넣어주고 반환
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
		for(PostAndUserVO pau : postList) {
			logger.info("postList: " + postList);
		}
		
		responseData.setCode(HttpStatus.OK);
		responseData.setMessage("SUCCESS");
		responseData.setData(postList);
		
		logger.info("전체 글 리스트 조회 API: " + responseData);
		
		return responseData;
	}
	
	// 내가 쓴 글 리스트 조회 API
	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseData findMyPost() throws Exception {
		logger.info("call findMyPost() method in PostController");
		return responseData;
	}

}
