package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.FeedVO;

public interface FeedDAO {
	
	public int insertFeed(FeedVO feedVO) throws DataAccessException;

	public List<FeedVO> findFolloweeByUser(Long userId) throws DataAccessException;

	public int deleteFeedByPostId(Long id) throws DataAccessException;

}
