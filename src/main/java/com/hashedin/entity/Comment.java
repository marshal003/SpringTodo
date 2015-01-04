package com.hashedin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.hashedin.service.model.CommentData;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	
	// One Task can have multiple comments.
	// There should be a foreign_key (task_id) to task table.
	@JoinColumn(name="task_id")
	@ManyToOne	
	private Task commentedOn;
	
	// One User can made multiple comments
	// There should be a foreign_key (user_id) to user table.
	@JoinColumn(name="user_id")
	@ManyToOne   
	private User commentedBy;
	
	private Date createdAt;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Task getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Task commentedOn) {
		this.commentedOn = commentedOn;
	}

	public User getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	private String commentText;
	
	@PrePersist
	public void prePersist(){
		Date now = new Date();
		this.createdAt = now;
	}
	
	public Comment(CommentData data){
		this.commentText = data.getCommentText();
	}
	
	public Comment(User commentedBy, Task commentdOn, String commentText){
		this.commentedBy = commentedBy;
		this.commentedOn = commentdOn;
		this.commentText = commentText;
	}
	
}
