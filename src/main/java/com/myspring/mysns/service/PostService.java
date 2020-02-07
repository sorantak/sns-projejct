package com.myspring.mysns.service;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.ResponseData;

public interface PostService {

	// �� ���� API
	// �� ����
	public ResponseData savePost(PostVO postVO, String accesstoken);

	// ��ü �� ����Ʈ ��ȸ API
	// ������ ���̺� ���� ��ü ��ȸ
	public ResponseData findAllPost() throws DataAccessException;

	// ���� �� �� ����Ʈ ��ȸ API
	public ResponseData findMyPost(String accesstoken) throws DataAccessException;

	// �� �� ��ȸ API
	public ResponseData postDetailById(Long id) throws DataAccessException;

	public ResponseData deletePostById(Long id) throws Exception;

}
