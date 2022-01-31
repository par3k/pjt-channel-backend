package com.happy.user.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.happy.user.domain.reqRegUserAccountDto;
import com.happy.user.domain.resAllUserAccountDto;

@Service
public class userAccountService {
	
	@Autowired
	private SqlSessionTemplate dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<resAllUserAccountDto> selEntireUserInfo() throws SQLException {
		return dao.selectList("com.happy.user.selEntireUserInfo");
	}
	
	public int userRegist(reqRegUserAccountDto reqDto) throws SQLException {
		String encPwd = passwordEncoder.encode(reqDto.getUserPwd());
		reqDto.setUserPwd(encPwd);
		return dao.insert("com.happy.user.userRegist", reqDto);
	}
}
