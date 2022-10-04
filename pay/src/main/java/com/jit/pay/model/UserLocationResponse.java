package com.jit.pay.model;

import java.time.LocalDateTime;
import java.util.List;

public class UserLocationResponse {

	private String userId;
	
	private List<Locations> locations;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Locations> getLocations() {
		return locations;
	}

	public void setLocations(List<Locations> arrayList) {
		this.locations = arrayList;
	}

}

