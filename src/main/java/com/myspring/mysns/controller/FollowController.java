package com.myspring.mysns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.service.FollowService;

@RestController
public class FollowController {
	
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Autowired
	private FollowService followService;

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public ResponseData followUser(@RequestBody FollowVO followeeId, @CookieValue(value = "accesstoken", required = false) String accesstoken) throws Exception {
		logger.info("call followUser()");
		return followService.followUser(followeeId, accesstoken);
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.DELETE)
	public ResponseData unfollowUser(@CookieValue(value = "accesstoken", required = false) String accesstoken) throws Exception {
		logger.info("call unfollowUser()");
		return null;
	}
	
	@RequestMapping(value = "/post/feed", method = RequestMethod.GET)
	public ResponseData viewMyFeedList(@CookieValue(value = "accesstoken", required = false) String accesstoken) throws Exception {
		logger.info("call viewMyFeedList()");
		return null;
	}
}
