package com.hashedin.service.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashedin.entity.Comment;
import com.hashedin.entity.Task;
import com.hashedin.entity.TaskPriority;
import com.hashedin.entity.TaskStatus;


public class TaskResponseData {

	@JsonProperty
	private Long taskId;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date updatedAt;
	
	@JsonProperty
	private Date dueDate;
	
	@JsonProperty
	private String notes;
	
	@JsonProperty
	private UserData createdBy;
	
	@JsonProperty
	private UserData assignedTo;
	
	@JsonProperty
	private List<Comment> comments;

	@JsonProperty
	private TaskStatus status;
	
	@JsonProperty
	private TaskPriority priority;
	
	@JsonProperty
	private String task;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public UserData getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserData createdBy) {
		this.createdBy = createdBy;
	}

	public UserData getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UserData assignedTo) {
		this.assignedTo = assignedTo;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public TaskResponseData(Task task){
		this.taskId = task.getTaskId();
		this.assignedTo = new UserData(task.getAssignedTo());
		this.comments = task.getComments();
		this.createdAt = task.getCreatedAt();
		this.updatedAt = task.getUpdatedAt();
		this.dueDate = task.getDueDate();
		this.createdBy = new UserData(task.getCreatedBy());
		this.notes = task.getNotes();
		this.priority = task.getPriority();
		this.status = task.getStatus();
		this.task = task.getTask();
	}
}
