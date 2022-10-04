package com.jit.pay.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class LocationSaveInfo {
	
	private double id;
	
	@NotBlank(message = "userId is mandatory")
	private String userId;

	@NotBlank(message = "createdOn is mandatory")
	private LocalDateTime createdOn;
	
	private Position location;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Position getLocation() {
		return location;
	}

	public void setLocation(Position location) {
		this.location = location;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}
	
}
