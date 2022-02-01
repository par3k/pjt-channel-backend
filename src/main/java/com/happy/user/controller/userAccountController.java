package com.happy.user.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.user.domain.reqRegUserAccountDto;
import com.happy.user.domain.resAllUserAccountDto;
import com.happy.user.service.userAccountService;
import com.mysql.cj.log.Slf4JLogger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = {"회원 API"})
public class userAccountController {

	@Autowired
	private userAccountService accountService;
	
	@PostMapping("/selEntireUserInfo")
	@ApiOperation(value="가입한 전체 회원 정보 조회")
	public List<resAllUserAccountDto> selEntireUserInfo() {
		try {
			log.info(">>> select All user Info");
			return accountService.selEntireUserInfo();
		} catch (SQLException e) {
			return null;
		}
	}
	
	@PostMapping("/userRegist")
	@ApiOperation(value="회원가입")
	public int userRegist(@RequestBody reqRegUserAccountDto reqDto) {
		try {
			log.info(">>> insert user info");
			return accountService.userRegist(reqDto);
		} catch (SQLException e) {
			return -999;
		}
	}
	
}
