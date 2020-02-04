package com.myspring.mysns.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.domain.PostVO;

public interface PostService {
	
	// �� ���� API
	// �� ����
	public int savePost(PostVO postVO) throws DataAccessException;
	
	// id�� �� ��ȸ
	public PostVO findPostById(Long id) throws DataAccessException;
	
	// ��ü �� ����Ʈ ��ȸ API
	// ������ ���̺� ���� ��ü ��ȸ
	public List<PostAndUserVO> findAllPost() throws DataAccessException;

}