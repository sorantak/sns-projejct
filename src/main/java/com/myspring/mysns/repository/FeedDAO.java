package com.myspring.mysns.repository;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.FeedVO;

public interface FeedDAO {
	
	public int insertFeed(FeedVO feedVO) throws DataAccessException;

}
