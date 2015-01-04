package com.hashedin.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashedin.entity.User;


@JsonIgnoreProperties
public class UserData {

	@JsonProperty
	private String firstName;
	
	@JsonProperty
	private String lastName;
	
	@JsonProperty
	private String fullName;
	
	@JsonProperty
	private String email;

	@JsonProperty
	private Long userId;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public UserData(){
		
	}
	
	public UserData(String firstName, String lastName, String email){
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserData(User user){
		if(user != null){
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.userId = user.getUserId();
			this.email = user.getEmail();
		}
	}
}
