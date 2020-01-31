package com.myspring.mysns.domain;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.myspring.mysns.controller.UserController;

@Component("userVO")
public class UserVO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private Long id;
	private String username;
	private String password;
	private Date created_at;
	
	// 변수 없는 생성자 필수
	public UserVO() {
		super();
	}

	public UserVO(Long id, String username, String password, Date created_at) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.created_at = created_at;
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
	
	public Date getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	@Override
	public String toString() {
		logger.info("call toString() method in UserVO");
		String info = "UserVO = [id: " + id+ ", username: " + username + ", password: " + password + ", created_at: " + created_at + "]";
		return info;
	}

}
