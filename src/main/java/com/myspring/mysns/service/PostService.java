package com.myspring.mysns.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.ResponseData;

public interface PostService {
	
	// 글 저장 API
	// 글 저장
	public int savePost(PostVO postVO) throws DataAccessException;
	
	// id로 글 조회
	public PostVO findPostById(Long id) throws DataAccessException;
	
	// 전체 글 리스트 조회 API
	// 계층형 테이블 만들어서 전체 조회
	public List<PostAndUserVO> findAllPost() throws DataAccessException;

	// 내가 쓴 글 리스트 조회  API
	public List<PostAndUserVO> findMyPost(Long id) throws DataAccessException;
	
	// 글 상세 조회 API
	public PostAndUserVO postDetailById(Long id) throws DataAccessException;

	public ResponseData deletePostById(Long id) throws DataAccessException;
	
}
