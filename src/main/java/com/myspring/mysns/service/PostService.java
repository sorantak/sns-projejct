package com.myspring.mysns.service;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.ResponseData;

public interface PostService {

	// 글 저장 API
	// 글 저장
	public ResponseData savePost(PostVO postVO, String accesstoken);

	// 전체 글 리스트 조회 API
	// 계층형 테이블 만들어서 전체 조회
	public ResponseData findAllPost() throws DataAccessException;

	// 내가 쓴 글 리스트 조회 API
	public ResponseData findMyPost(String accesstoken) throws DataAccessException;

	// 글 상세 조회 API
	public ResponseData postDetailById(Long id) throws DataAccessException;

	public ResponseData deletePostById(Long id) throws Exception;

}
