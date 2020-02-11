package com.myspring.mysns.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FollowVO {

	private Long followeeId;
	private Long followerId;
	private String createdAt;

	public FollowVO() {
		super();
	}

	public Long getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(Long followeeId) {
		this.followeeId = followeeId;
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		Date now = new Date();
		SimpleDateFormat B = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateform = B.format(now);
		this.createdAt = dateform;
	}

	@Override
	public String toString() {
		return "FollowVO [followeeId=" + followeeId + ", followerId=" + followerId + ", createdAt=" + createdAt + "]";
	}

}
