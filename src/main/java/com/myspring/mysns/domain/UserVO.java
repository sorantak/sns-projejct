package com.myspring.mysns.domain;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.myspring.mysns.controller.UserController;

@Component("userVO")
public class UserVO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// mysql에서는 _at이 허용되지만 java에서는 안됨
	private Long id;
	private String username;
	//
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String createdAt;
	private boolean isFollow = true;
	
	// 변수 없는 생성자 필수
	public UserVO() {
		super();
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
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt() {
	      Date now = new Date();
	      SimpleDateFormat B = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      String dateform = B.format(now);
	      this.createdAt = dateform;
	   }

	public boolean getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}

	@Override
	public String toString() {
		logger.info("call toString() in UserVO");
		return "UserVO [id=" + id + ", username=" + username + ", password=" + password + ", createdAt=" + createdAt
				+ ", isFollow=" + isFollow + "]";
	}

}
