package com.jit.pay.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jit.pay.entity.LocationDetails;
import com.jit.pay.entity.UserDetails;
import com.jit.pay.model.LocationSaveInfo;
import com.jit.pay.model.Locations;
import com.jit.pay.model.Position;
import com.jit.pay.model.UserLocationResponse;
import com.jit.pay.model.UserRequest;
import com.jit.pay.model.UserSingleResponse;
import com.jit.pay.repository.LocationDetailsRepo;
import com.jit.pay.repository.UserDetailsRepo;
import com.jit.pay.service.JitService;

@Service
public class JitServiceImpl implements JitService {

	@Autowired
	UserDetailsRepo userDetailsRepo;
	
	@Autowired
	LocationDetailsRepo locationDetailsRepo;

	@Override
	public void updateUserDetails(UserRequest userRequest) {
		
		UserDetails userDetails = new UserDetails();
		if(userRequest.getCreatedOn() != null) {
			userDetails.setCreatedOn(userRequest.getCreatedOn());
		} else {
			userDetails.setCreatedOn(LocalDateTime.now());
		}
		userDetails.setEmail(userRequest.getEmail());
		userDetails.setFirstName(userRequest.getFirstName());
		userDetails.setSecondName(userRequest.getSecondName());
		userDetails.setUserId(userRequest.getUserId());
		userDetailsRepo.save(userDetails);
	}

	@Override
	public UserSingleResponse getUserData(String id) {
		
		Optional<UserDetails> userDetailOptional = userDetailsRepo.findById(id);
		UserSingleResponse userSingleResponse = new UserSingleResponse();
		
		if (userDetailOptional.isPresent()) {
			UserDetails userDetail = userDetailOptional.get();
			userSingleResponse.setCreatedOn(userDetail.getCreatedOn());
			userSingleResponse.setEmail(userDetail.getEmail());
			userSingleResponse.setFirstName(userDetail.getFirstName());
			userSingleResponse.setLocation(new Position());
			userSingleResponse.setSecondName(userDetail.getSecondName());
			userSingleResponse.setUserId(userDetail.getUserId());
			if (!userDetail.getLocationDetails().isEmpty()) {
				Collections.sort(userDetail.getLocationDetails());
				userSingleResponse.getLocation().setLatitude(userDetail.getLocationDetails().get(0).getLatitude());
				userSingleResponse.getLocation().setLongitude(userDetail.getLocationDetails().get(0).getLongitude());
			}
		}
		return userSingleResponse;
	}

	@Override
	public UserLocationResponse getUserDateWithRange(String id, LocalDateTime fromDate, LocalDateTime toDate) {
		Optional<UserDetails> userDetailOptional = userDetailsRepo.findById(id);
		UserLocationResponse userLocationResponse = new UserLocationResponse();
		
		if (userDetailOptional.isPresent()) {
			UserDetails userDetail = userDetailOptional.get();
			userLocationResponse.setUserId(userDetail.getUserId());
			if (!userDetail.getLocationDetails().isEmpty()) {
				
				Predicate<LocationDetails> statementPredicate = generatePredicate(fromDate, toDate);
				userDetail.setLocationDetails(filteredAttribute(userDetail.getLocationDetails(), statementPredicate));
				
				if (!userDetail.getLocationDetails().isEmpty()) {
					updateLocation(userLocationResponse,userDetail.getLocationDetails());
				}
			}
		}
		
		return userLocationResponse;
	}

	private void updateLocation(UserLocationResponse userLocationResponse, List<LocationDetails> locationDetails) {
		userLocationResponse.setLocations(new ArrayList());
		for(LocationDetails locationDetail :locationDetails) {
			Position position = new Position();
			position.setLatitude(locationDetail.getLatitude());
			position.setLongitude(locationDetail.getLongitude());
			Locations locations = new Locations();
			locations.setLocation(position);
			locations.setCreatedOn(locationDetail.getCreatedOn());
			userLocationResponse.getLocations().add(locations);
		}
		
	}

	@Override
	public LocationSaveInfo saveLocationInfo(LocationSaveInfo locationSaveInfo) {
		LocationDetails locationDetails = new LocationDetails();
		locationDetails.setCreatedOn(locationSaveInfo.getCreatedOn());
		locationDetails.setUserId(locationSaveInfo.getUserId());
		locationDetails.setLatitude(locationSaveInfo.getLocation().getLatitude());
		locationDetails.setLongitude(locationSaveInfo.getLocation().getLongitude());
		locationSaveInfo.setId(locationDetailsRepo.save(locationDetails).getId());
		return locationSaveInfo;
	}
	
	private Predicate<LocationDetails> generatePredicate(LocalDateTime fromDate, LocalDateTime toDate) {
		return locationDetail -> (locationDetail.getCreatedOn().compareTo(fromDate) >= 0
				&& locationDetail.getCreatedOn().compareTo(toDate) <= 0);
	}

	private List<LocationDetails> filteredAttribute(List<LocationDetails> statement,
			Predicate<LocationDetails> statementPredicate) {
		statement = statement.stream().filter(statementPredicate).collect(Collectors.toList());
		return statement;
	}

}
