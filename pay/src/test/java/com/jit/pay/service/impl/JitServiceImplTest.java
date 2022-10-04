package com.jit.pay.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jit.pay.entity.LocationDetails;
import com.jit.pay.entity.UserDetails;
import com.jit.pay.model.LocationSaveInfo;
import com.jit.pay.model.Position;
import com.jit.pay.model.UserRequest;
import com.jit.pay.repository.LocationDetailsRepo;
import com.jit.pay.repository.UserDetailsRepo;

@ExtendWith(MockitoExtension.class)
class JitServiceImplTest {

	@InjectMocks
	JitServiceImpl jitServiceImpl;

	@Mock
	UserDetailsRepo userDetailsRepo = Mockito.mock(UserDetailsRepo.class);

	@Mock
	LocationDetailsRepo locationDetailsRepo = Mockito.mock(LocationDetailsRepo.class);

	UserRequest userRequest;
	UserDetails userDetails;
	LocationDetails locationDetails;
	LocationSaveInfo locationSaveInfo;
	Position location;

	CharSequence date = "2022-02-08T11:44:00.524";
	String dateFormate = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	@BeforeEach
	public void setup() {

		userRequest = new UserRequest();
		userRequest.setCreatedOn(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormate)));
		userRequest.setFirstName("Alex");
		userRequest.setEmail("alex.schmid@gmail.com");
		userRequest.setSecondName("Schmid");
		userRequest.setUserId("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2");

		location = new Position();
		location.setLatitude(52.25742342295784);
		location.setLongitude(10.540583401747602);

		locationSaveInfo = new LocationSaveInfo();
		locationSaveInfo.setCreatedOn(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormate)));
		locationSaveInfo.setId(1);
		locationSaveInfo.setLocation(location);
		locationSaveInfo.setUserId("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2");

		locationDetails = new LocationDetails();
		locationDetails.setCreatedOn(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormate)));
		locationDetails.setId(1l);
		locationDetails.setLatitude(52.25742342295784);
		locationDetails.setLongitude(10.540583401747602);
		locationDetails.setUserId("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2");

		userDetails = new UserDetails();
		userDetails.setCreatedOn(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormate)));
		userDetails.setEmail("alex.schmid@gmail.com");
		userDetails.setFirstName("Alex");
		userDetails.setSecondName("Schmid");
		userDetails.setUserId("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2");
		userDetails.setLocationDetails(new ArrayList<>());

		userDetails.getLocationDetails().add(locationDetails);

	}

	@DisplayName("JUnit test for updateUserDetails method")
	@Test
	void updateUserDetailsTest() {
		when(userDetailsRepo.save(userDetails)).thenReturn(userDetails);
		jitServiceImpl.updateUserDetails(userRequest);
	}

	@DisplayName("JUnit test for getUserData method")
	@Test
	void getUserDataTest() {
		when(userDetailsRepo.findById("1")).thenReturn(Optional.of(userDetails));
		assertEquals(10.540583401747602, jitServiceImpl.getUserData("1").getLocation().getLongitude());
	}

	@DisplayName("JUnit test for getUserDateWithRange method")
	@Test
	void getUserDateWithRange() {
		when(userDetailsRepo.findById("1")).thenReturn(Optional.of(userDetails));
		assertTrue(!jitServiceImpl
				.getUserDateWithRange("1",
						LocalDateTime.parse("2020-02-08T11:44:00.524", DateTimeFormatter.ofPattern(dateFormate)),
						LocalDateTime.parse("2023-02-08T11:44:00.524", DateTimeFormatter.ofPattern(dateFormate)))
				.getLocations().isEmpty());

		assertNull(jitServiceImpl
				.getUserDateWithRange("1",
						LocalDateTime.parse("2023-02-03T11:44:00.524", DateTimeFormatter.ofPattern(dateFormate)),
						LocalDateTime.parse("2023-02-08T11:44:00.524", DateTimeFormatter.ofPattern(dateFormate)))
				.getLocations());
	}

//	@DisplayName("JUnit test for saveLocationInfo method")
//	@Test
//	void saveLocationInfo() {
//		when(locationDetailsRepo.save(locationDetails)).thenReturn(locationDetails);
//		assertEquals(1l, jitServiceImpl.saveLocationInfo(locationSaveInfo).getId());
//	}
}
