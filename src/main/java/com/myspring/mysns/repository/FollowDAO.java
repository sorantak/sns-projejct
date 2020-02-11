package com.myspring.mysns.repository;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.FollowVO;

public interface FollowDAO {
	
	// follow user
	public int followUser(FollowVO followeeId) throws DataAccessException;

	public int unfollowUser(FollowVO followeeId) throws DataAccessException;

}
