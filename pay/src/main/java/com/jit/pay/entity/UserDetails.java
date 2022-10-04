package com.jit.pay.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users_table")
public class UserDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ID")
	private String userId;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
	private List<LocationDetails> locationDetails;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "SECOND_NAME")
	private String secondName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "CREATED_ON")
	private LocalDateTime createdOn;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<LocationDetails> getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(List<LocationDetails> locationDetails) {
		this.locationDetails = locationDetails;
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

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetails other = (UserDetails) obj;
		return Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", locationDetails=" + locationDetails + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", email=" + email + ", createdOn=" + createdOn + "]";
	}
	
}
