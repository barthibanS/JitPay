package com.jit.pay.service;

import java.time.LocalDateTime;

import com.jit.pay.model.LocationSaveInfo;
import com.jit.pay.model.UserDetailsResponse;
import com.jit.pay.model.UserLocationResponse;
import com.jit.pay.model.UserRequest;
import com.jit.pay.model.UserSingleResponse;

public interface JitService {

	public void updateUserDetails(UserRequest userRequest);

	public UserSingleResponse getUserData(String id);

	public UserLocationResponse getUserDateWithRange(String id, LocalDateTime fromDate, LocalDateTime toDate);

	public LocationSaveInfo saveLocationInfo(LocationSaveInfo locationSaveInfo);

}
