package com.hashedin.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.hashedin.service.model.UserData;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	@OneToMany(mappedBy="createdBy", fetch = FetchType.LAZY)
	private List<Task> tasksCreated;

	@OneToMany(mappedBy="assignedTo", fetch = FetchType.LAZY)
	private List<Task> tasksAssigned;
	
	public List<Task> getTasksAssigned() {
		return tasksAssigned;
	}

	public void setTasksAssigned(List<Task> tasksAssigned) {
		this.tasksAssigned = tasksAssigned;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/* Indicate Entity Manager to not save this column.
	 * Pushed out this method into UserData model. 
	 * 
	@Transient
	public String getFullName() {
		return String.format("%s %s", this.lastName, this.firstName);
	}
	*/

	public List<Task> getTasksCreated() {
		return tasksCreated;
	}

	public void setTasksCreated(List<Task> tasksCreated) {
		this.tasksCreated = tasksCreated;
	}
	
	public User(){
		
	}
	public User(UserData data){
		this.firstName = data.getFirstName();
		this.lastName = data.getLastName();
		this.email = data.getEmail();
	}
	
	public User(String firstName, String lastName, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
}
