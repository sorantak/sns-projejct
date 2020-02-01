package com.myspring.mysns.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// @Component �ʼ�
@Component
public class TokenVO {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenVO.class);
	
	private String token;
	private Long userId;
	private String createdAt;
	
	// ������
	public TokenVO() {
		super();
	}
	
	// Alt+Shft+S �ϸ� ���� getter, setter, toString method �ҷ��� �� ����
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		logger.info("call toString() method in TokenVO");
		return "TokenVO [token=" + token + ", userId=" + userId + ", createdAt=" + createdAt + "]";
	}
	
}
