package com.happy.user.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.happy.user.domain.reqUserAccountDto;
import com.happy.user.domain.resAllUserAccountDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class userAccountService {
	

	private final SqlSessionTemplate dao;
	
	private final PasswordEncoder passwordEncoder;

	/*
	 * 전체 회원 조회 (Test)
	 * result return : List
	 */
	public List<resAllUserAccountDto> selEntireUserInfo() throws SQLException {
		return dao.selectList("com.happy.user.selEntireUserInfo");
	}
	
	/*
	 * 회원가입
	 * requst parameter : DTO
	 * result return : Integer
	 */
	public int userRegist(reqUserAccountDto reqDto) throws SQLException {
		// 고객 비밀번호 암호화
		String encPwd = passwordEncoder.encode(reqDto.getUserPwd());
		reqDto.setUserPwd(encPwd);
		log.debug("reqDto >>>>" + reqDto.toString());
		return dao.insert("com.happy.user.userRegist", reqDto);
	}
	
	/*
	 * 로그인
	 * requst parameter : DTO
	 * result return : List
	 */
	public List<resAllUserAccountDto> login(reqUserAccountDto reqDto) throws SQLException {
		List<resAllUserAccountDto> resDto = null;
		String originalPwd = dao.selectOne("com.happy.user.getPwd", reqDto);
		boolean result = passwordEncoder.matches(reqDto.getUserPwd(), originalPwd);
		if (result) {
			resDto = dao.selectList("com.happy.user.login", reqDto);
		}
		return resDto;
	}
	
	/*
	 * 회원정보 수정
	 * requst parameter : DTO
	 * result return : Integer
	 */
	public int userModify(reqUserAccountDto reqDto) throws SQLException {
		Integer userMngtNo = (Integer)dao.selectOne("com.happy.user.selUserMngtNo", reqDto);
		reqDto.setUserMngtNo(userMngtNo);
		log.debug("reqDto >>>>" + reqDto.toString());
		return dao.update("com.happy.user.userModify", reqDto);
	}
	
	/*
	 * 회원탈퇴 (실제데이터를 삭제하는 작업아님)
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userDelete(reqUserAccountDto reqDto) throws SQLException {
		Integer userMngtNo = (Integer)dao.selectOne("com.happy.user.selUserMngtNo", reqDto);
		reqDto.setUserMngtNo(userMngtNo);
		log.debug("reqDto >>>>" + reqDto.toString());
		return dao.update("com.happy.user.userDelete", reqDto);
	}
}