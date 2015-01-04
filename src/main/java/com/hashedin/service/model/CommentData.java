package com.hashedin.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentData {

	@JsonProperty("taskId")
	private Long taskId;
	
	@JsonProperty("userId")
	private Long userId;
	
	@JsonProperty("commentText")
	private String commentText;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}
