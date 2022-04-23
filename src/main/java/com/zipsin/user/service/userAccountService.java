package com.zipsin.user.service;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zipsin.user.domain.reqUserAccountDto;
import com.zipsin.user.domain.resLoginDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userAccountService {
	
	private final SqlSessionTemplate dao;
	private final PasswordEncoder passwordEncoder;
	
	/*
	 * 회원가입
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userRegist(reqUserAccountDto reqDto) throws SQLException {
		// 이메일 주소 중복 확인
		Integer validation = dao.selectOne(getDomain("emailVal"), reqDto.getEmailAdr());
		if (validation > 0) {
			return -999;
		}
		// 비밀번호 암호화
		String encPwd = passwordEncoder.encode(reqDto.getUserPwd());
		reqDto.setUserPwd(encPwd);
		return dao.insert(getDomain("userRegist"), reqDto);
	}
	
	/*
	 * 로그인
	 * request parameter : DTO
	 * result return : List
	 */
	public List<resLoginDto> login(reqUserAccountDto reqDto) throws SQLException {
		List<resLoginDto> resDto = null;
		// 암호화되어 DB에 저장되어있는 비밀번호를 먼저 갖고옴
		String encPwd = dao.selectOne(getDomain("selEncPwd"), reqDto);
		// 입력받은 비밀번호와 암호화된 비밀번호가 같은지 비교. (같을시 : True, 다를시 : False)
		boolean result = passwordEncoder.matches(reqDto.getUserPwd(), encPwd);
		if (result) {
			resDto = dao.selectList(getDomain("login"), reqDto);
			
		}
		return resDto;
	}
	
	/*
	 * 회원정보 수정
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userModify(reqUserAccountDto reqDto) throws SQLException {
		Integer userMngtNo = (Integer)dao.selectOne(getDomain("selUserMngtNo"), reqDto);
		reqDto.setUserMngtNo(userMngtNo);
		return dao.update(getDomain("userModify"), reqDto);
	}
	
	/*
	 * 회원탈퇴 (실제데이터를 삭제하는 작업아님)
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userDelete(reqUserAccountDto reqDto) throws SQLException {
		Integer userMngtNo = (Integer)dao.selectOne(getDomain("selUserMngtNo"), reqDto);
		reqDto.setUserMngtNo(userMngtNo);
		return dao.update(getDomain("userDelete"), reqDto);
	}
	
	private String getDomain(String id) {
		return "com.zipsin.user.".concat(id);
	}
}