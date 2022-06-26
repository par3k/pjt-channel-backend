package com.zipsin.user.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zipsin.user.domain.reqLoginDto;
import com.zipsin.user.domain.reqUserAccountDto;
import com.zipsin.user.service.UserAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@Api(tags = {"회원 API"})
@RequiredArgsConstructor
// COR 방지를 위해 CrossOrigin 지정
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserAccountController {

	private final UserAccountService accountService;
	
	@PostMapping("/userRegist")
	@ApiOperation(value="회원가입")
	public int userRegist(@RequestBody reqUserAccountDto reqDto) {
		try {
			return accountService.userRegist(reqDto);
		} catch (SQLException e) {
			return -999;
		}
	}
	
	@PostMapping("/login")
	@ApiOperation(value="로그인(토큰생성)")
	public ResponseEntity<Map<String, Object>> login(@RequestBody reqLoginDto reqDto) {
		try {
			return accountService.login(reqDto);
		} catch (SQLException e) {
			return null;
		}
	}
	
	@PostMapping("/userModify")
	@ApiOperation(value="회원정보 수정")
	public int userModify(@RequestBody reqUserAccountDto reqDto) {
		try {
			return accountService.userModify(reqDto);
		} catch (SQLException e) {
			return -999;
		}
	}
	
	/*
	 * 실제로 데이터를 DB에 삭제하지 않는다.
	 */
	@PostMapping("/userDelete")
	@ApiOperation(value="회원탈퇴")
	public int userDelete(@RequestBody reqUserAccountDto reqDto) {
		try {
			return accountService.userDelete(reqDto);
		} catch(SQLException e) {
			return -999;
		}
	}
	
}
