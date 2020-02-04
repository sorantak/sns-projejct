package com.myspring.mysns.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.repository.PostDAO;

@Service
public class PostServiceImpl implements PostService {
	
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	
	@Autowired
	private PostDAO postDAO;

	// �� ���� API
	// �� ����
	@Override
	public int savePost(PostVO postVO) throws DataAccessException {
		logger.info("call savePost() method in PostServiceImpl");
		return postDAO.savePost(postVO);
	}
	
	// id�� �� ��ȸ
	@Override
	public PostVO findPostById(Long id) throws DataAccessException {
		logger.info("call findPostById() method in PostServiceImpl");
		return postDAO.findPostById(id);
	}

	// ��ü �� ����Ʈ ��ȸ API
	// ������ ���̺� ���� ��ü ��ȸ
	@Override
	public List<PostAndUserVO> findAllPost() throws DataAccessException {
		logger.info("call findAllPost() method in PostServiceImpl");
		return postDAO.findAllPost();
	}
	
}
