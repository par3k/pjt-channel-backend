package com.happy.user.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.user.domain.resRegUserAccountDto;

@Service
public class userAccountService {
	
	@Autowired
	SqlSessionTemplate dao;
	
	public List<resRegUserAccountDto> selEntireUserInfo() throws SQLException {
		return dao.selectList("com.happy.user.selEntireUserInfo");
	}
}
