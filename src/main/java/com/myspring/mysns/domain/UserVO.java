package com.myspring.mysns.domain;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.myspring.mysns.controller.UserController;

@Component("userVO")
public class UserVO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// mysql에서는 _at이 허용되지만 java에서는 안됨
	private Long id;
	private String username;
	private String password;
	private Date createdAt;
	
	// 변수 없는 생성자 필수
	public UserVO() {
		super();
	}
	
	// 이 생성자는 필요없는 것?
	public UserVO(Long id, String username, String password, Date createdAt) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
	}
	
	// getter, setter로 변수들을 가져옴
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		logger.info("call toString() method in UserVO");
		return "UserVO [id=" + id + ", username=" + username + ", password=" + password + ", createdAt=" + createdAt + "]";
	}

}
