package com.happy.user.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.user.domain.reqUserAccountDto;
import com.happy.user.domain.resAllUserAccountDto;
import com.happy.user.service.userAccountService;

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
	@ApiOperation(value="가입한 전체 회원 정보 조회 (곧 삭제예정)")
	public List<resAllUserAccountDto> selEntireUserInfo() {
		try {
			log.info(">>>>>>>> CMD : select All user Info");
			return accountService.selEntireUserInfo();
		} catch (SQLException e) {
			return null;
		}
	}
	
	@PostMapping("/userRegist")
	@ApiOperation(value="회원가입")
	public int userRegist(@RequestBody reqUserAccountDto reqDto) {
		try {
			log.info(">>>>>>>> CMD : insert user info");
			return accountService.userRegist(reqDto);
		} catch (SQLException e) {
			return -999;
		}
	}
	
	@PostMapping("/userModify")
	@ApiOperation(value="회원정보 수정")
	public int userModify(@RequestBody reqUserAccountDto reqDto) {
		try {
			log.info(">>>>>>>> CMD : update user info");
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
			log.info(">>>>>>>> CMD : delete user info");
			return accountService.userDelete(reqDto);
		} catch(SQLException e) {
			return -999;
		}
	}
	
}
