package com.hashedin.service.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashedin.entity.TaskPriority;

@JsonIgnoreProperties
public class TaskRequestData {

	@JsonProperty("task")
	private String task;
	
	@JsonProperty("dueDate")
	private Date dueDate;
	
	@JsonProperty("createdBy")
	private Long createdBy;
	
	@JsonProperty("notes")
	private String notes;
	
	@JsonProperty("priority")
	private TaskPriority priority;

	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
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

	public TaskRequestData(){
		
	}
	
	public TaskRequestData(String task, Long userId){
		this.createdBy = userId;
		this.task = task;
	}
	
	public TaskRequestData(TaskRequestData data){
		this.task = data.task;
		this.createdBy = data.createdBy;
		this.dueDate = data.dueDate;
		this.priority = data.priority;
		this.notes = data.notes;
	}
	
}
