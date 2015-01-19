package com.hashedin.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.hashedin.service.model.TaskData;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	private String task;

	private String description;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdAt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedAt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dueDate;

	private String notes;

	@ManyToOne
	// One User can create multiple tasks.
	@JoinColumn(name = "createdBy")
	private User createdBy;

	@ManyToOne
	// One User can have multiple assigned tasks.
	@JoinColumn(name = "assignedTo")
	private User assignedTo;

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	@OneToMany(mappedBy = "commentedOn", orphanRemoval = true)
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@Enumerated(EnumType.STRING)
	private TaskPriority priority;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public DateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist
	public void prePersist() {
		DateTime now = new DateTime();
		this.createdAt = now;
		this.updatedAt = now;
		if (this.priority == null) {
			this.priority = TaskPriority.HIGH;
		}
		if (this.status == null) {
			this.status = TaskStatus.CREATED;
		}
		if (this.dueDate == null) {
			this.setDueDate(this.createdAt);
		}
	}

	@PreUpdate
	public void preUpdate() {
		DateTime now = new DateTime();
		this.updatedAt = now;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Task() {

	}

	public Task(TaskData data) {
		this.task = data.getTask();
		this.notes = data.getNotes();
		this.priority = data.getPriority();
		this.dueDate = data.getDueDate();
	}

	public Task(User createdBy, String task) {
		this.createdBy = createdBy;
		this.task = task;
	}

	public Task(User user, String task, TaskPriority priority) {
		this(user, task);
		this.priority = priority;
	}

	public Task(User user, String task, TaskPriority priority, String notes) {
		this(user, task, priority);
		this.notes = notes;
	}

}
