package com.happy.common.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	@DisplayName("패스워드 암호화 테스트")
	void passwordEncode() {
		// given
		String testPwd = "1q2w3e4r!!";
		// when
		String encTestPwd = passwordEncoder.encode(testPwd);
		// then
		assertAll(
				() -> assertNotEquals(testPwd, encTestPwd),
				() -> assertTrue(passwordEncoder.matches(testPwd, encTestPwd))
				);
	}

}
