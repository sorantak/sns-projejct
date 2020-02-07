package com.myspring.mysns.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.ResponseData;
import com.myspring.mysns.domain.PostAndUserVO;
import com.myspring.mysns.repository.PostDAO;

@Service
public class PostServiceImpl implements PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostDAO postDAO;

	@Autowired
	ResponseData responseData;

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

	// ���� �� �� ����Ʈ ��ȸ API
	@Override
	public List<PostAndUserVO> findMyPost(Long id) throws DataAccessException {
		logger.info("call findMyPost() method in PostServiceImpl");
		return postDAO.findMyPost(id);
	}

	// �� �� ��ȸ API
	@Override
	public PostAndUserVO postDetailById(Long id) throws DataAccessException {
		logger.info("call postDetailById() method in PostServiceImpl");
		return postDAO.postDetailById(id);
	}

	@Override
	public ResponseData deletePostById(Long id) throws DataAccessException {
		logger.info("call deletePostById() method in PostServiceImpl");

		// MyBatis ������ ȣ�� �Ŀ� ���ϰ��� ������ 1, ���н� 0�� �ȴ�.
		int result = postDAO.deletePostById(id);
		logger.info("result: " + result);

		if (result == 1) {
			responseData.setCode(HttpStatus.OK);
			responseData.setMessage("SUCCESS");
			responseData.setData(null);
		} else {
			responseData.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			responseData.setMessage("ERROR");
			responseData.setData(null);
		}
		
		return responseData;
	}

}
