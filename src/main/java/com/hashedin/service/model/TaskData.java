package com.hashedin.service.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashedin.entity.TaskPriority;

@JsonIgnoreProperties
public class TaskData {

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	@JsonProperty("task")
	private String task;

	@JsonProperty("dueDate")
	private DateTime dueDate;

	@JsonProperty("createdBy")
	private Long createdBy;

	@JsonProperty("notes")
	private String notes;

	@JsonProperty("priority")
	private TaskPriority priority;

	@JsonProperty("assignedTo")
	private Long assignedTo;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public DateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public TaskData() {

	}

	public TaskData(String task, Long userId) {
		this.createdBy = userId;
		this.task = task;
	}

	public TaskData(TaskData data) {
		this.task = data.task;
		this.createdBy = data.createdBy;
		this.dueDate = data.dueDate;
		this.priority = data.priority;
		this.notes = data.notes;
	}

}
