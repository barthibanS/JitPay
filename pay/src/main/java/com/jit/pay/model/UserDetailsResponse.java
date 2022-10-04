package com.jit.pay.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class UserDetailsResponse {

	@NotBlank(message = "userId is mandatory")
	private String userId;
	
	@NotBlank(message = "firstName is mandatory")
	private String firstName;
	
	@NotBlank(message = "secondName is mandatory")
	private String secondName;
	
	@NotBlank(message = "email is mandatory")
	private String email;
	
	private LocalDateTime createdOn;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
