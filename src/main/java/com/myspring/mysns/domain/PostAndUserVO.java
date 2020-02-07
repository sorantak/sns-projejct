package com.myspring.mysns.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PostAndUserVO {
	
	private static final Logger logger = LoggerFactory.getLogger(PostAndUserVO.class);
	
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private String createdAt;
	private UserVO User;
	
	public PostAndUserVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public UserVO getUser() {
		return User;
	}

	public void setUser(UserVO user) {
		User = user;
	}

	@Override
	public String toString() {
		logger.info("call toString() method in PostAndUserVO");

		return "PostAndUserVO [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", createdAt=" + createdAt + ", User=" + User + "]";
	}
	
}
