package com.jit.pay.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jit.pay.model.LocationSaveInfo;
import com.jit.pay.model.UserRequest;
import com.jit.pay.service.JitService;

@RestController
public class Control {

	Logger logger = LoggerFactory.getLogger(Control.class);

	@Autowired
	private JitService jitService;

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> getUserDetail(@PathVariable(name = "id", required = true) String id) {
		return new ResponseEntity<>(jitService.getUserData(id), HttpStatus.OK);
	}

	@GetMapping(value = "/userlocation/{id}")
	public ResponseEntity<?> getUserLocation(@PathVariable(name = "id", required = true) String id,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
		return new ResponseEntity<>(jitService.getUserDateWithRange(id, fromDate, toDate), HttpStatus.OK);
	}

	@PostMapping(value = "/user")
	public void saveOrUpdateUser(@RequestBody UserRequest userRequest) {
		jitService.updateUserDetails(userRequest);
	}

	@PostMapping(value = "/info")
	public ResponseEntity<?> saveLocationInfo(@RequestBody LocationSaveInfo locationSaveInfo) {
		return new ResponseEntity<>(jitService.saveLocationInfo(locationSaveInfo), HttpStatus.OK);
	}

}
