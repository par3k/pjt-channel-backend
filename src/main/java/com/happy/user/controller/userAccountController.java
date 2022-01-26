package com.happy.user.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.user.domain.resRegUserAccountDto;
import com.happy.user.service.userAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = {"ȸ������ API"})
public class userAccountController {

	@Autowired
	private userAccountService accountService;
	
	@PostMapping("/selEntireUserInfo")
	@ApiOperation(value="������ ȸ�� ��ü ��ȸ")
	public List<resRegUserAccountDto> selEntireUserInfo() {
		try {
			return accountService.selEntireUserInfo();
		} catch (SQLException e) {
			return null;
		}
	}
}
