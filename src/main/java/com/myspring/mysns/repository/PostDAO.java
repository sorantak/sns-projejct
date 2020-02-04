package com.myspring.mysns.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.PostAndUserVO;

public interface PostDAO {
	
	// �� ���� API
	// �� ����
	public int savePost(PostVO postVO) throws DataAccessException;
	
	// id�� �� ��ȸ
	public PostVO findPostById(Long id) throws DataAccessException;
	
	// ��ü �� ����Ʈ ��ȸ API
	// ������ ���̺� ���� ��ü ��ȸ
	public List<PostAndUserVO> findAllPost() throws DataAccessException;
	
}