package com.happy.user.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.happy.user.domain.reqRegUserAccountDto;
import com.happy.user.domain.resAllUserAccountDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class userAccountService {
	
	@Autowired
	SqlSessionTemplate dao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	/*
	 * 전체 회원 조회 (Test)
	 * result return : List
	 */
	public List<resAllUserAccountDto> selEntireUserInfo() throws SQLException {
		return dao.selectList("com.happy.user.selEntireUserInfo");
	}
	
	/*
	 * 회원가입
	 * result return : Integer
	 */
	public int userRegist(reqRegUserAccountDto reqDto) throws SQLException {
		// 고객 비밀번호 암호화
		String encPwd = passwordEncoder.encode(reqDto.getUserPwd());
		reqDto.setUserPwd(encPwd);
		log.debug("reqDto >>>>" + reqDto.toString());
		return dao.insert("com.happy.user.userRegist", reqDto);
	}
}