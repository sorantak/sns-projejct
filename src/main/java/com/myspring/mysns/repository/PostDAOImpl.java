package com.myspring.mysns.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.mysns.domain.PostVO;
import com.myspring.mysns.domain.PostAndUserVO;

@Repository
public class PostDAOImpl implements PostDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PostDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.myspring.mysns.mappers.mappers";
	
	// �� ���� API
	// �� ����
	@Override
	public int savePost(PostVO postVO) throws DataAccessException {
		logger.info("call savePost() method in PostDAOImpl");
		
		int result = sqlSession.insert(Namespace + ".savePost", postVO);
		return result;
	}

	// id�� �� ��ȸ
	@Override
	public PostVO findPostById(Long id) throws DataAccessException {
		logger.info("call findPostById() method in PostDAOImpl");
		
		PostVO result = sqlSession.selectOne(Namespace + ".findPostById", id);
		return result;
	}
	
	// ��ü �� ����Ʈ ��ȸ API
	// ������ ���̺� ���� ��ü ��ȸ
	@Override
	public List<PostAndUserVO> findAllPost() throws DataAccessException {
		logger.info("call findAllPost() method in PostDAOImpl");
		
		List<PostAndUserVO> result = sqlSession.selectList(Namespace + ".findAllPost");
		return result;
	}

	// ���� �� �� ����Ʈ ��ȸ  API
	@Override
	public List<PostAndUserVO> findMyPost(Long id) throws DataAccessException {
		logger.info("call findMyPost() method in PostDAOImpl");
		
		List<PostAndUserVO> result = sqlSession.selectList(Namespace + ".findMyPost", id);
		return result;
	}


}
