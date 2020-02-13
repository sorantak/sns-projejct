package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.FollowVO;

public interface FollowDAO {

	// follow user
	public int followUser(FollowVO followeeIdInVO) throws DataAccessException;

	public int unfollowUser(FollowVO followeeId) throws DataAccessException;
	
	public List<FollowVO> findFollowersByFollowee(Long followeeId) throws DataAccessException;


}
