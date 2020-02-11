package com.myspring.mysns.service;

import com.myspring.mysns.domain.FollowVO;
import com.myspring.mysns.domain.ResponseData;

public interface FollowService {

	public ResponseData followUser(FollowVO followeeId, String accesstoken);

	public ResponseData unfollowUser(FollowVO followeeId, String accesstoken);

}
