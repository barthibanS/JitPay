package com.jit.pay.model;

import java.time.LocalDateTime;

public class Locations {

	private LocalDateTime createdOn;
	
	private Position location;

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

}
