package com.jit.pay.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jit.pay.service.JitService;

@SpringBootTest
class ControlTest {

	@Autowired
	private JitService jitService;

	@Test
	void getUserDetailTest() {
		assertNotNull(jitService);
	}
}
