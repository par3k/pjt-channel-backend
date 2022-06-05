package com.zipsin.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zipsin.user.domain.reqLoginDto;
import com.zipsin.user.domain.reqUserAccountDto;
import com.zipsin.user.domain.resLoginDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAccountService.class);
	
	private final SqlSessionTemplate dao;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	/*
	 * 회원가입
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userRegist(reqUserAccountDto reqDto) throws SQLException{
		// 이메일 주소가 유효한지 확인
		Integer validation = this.validationEmail(reqDto.getEmailAdr());
		if (validation == 1) {
			return -998; // 이미 존재하는 이메일 주소입니다.
		}
		// 비밀번호 암호화
		String encPwd = passwordEncoder.encode(reqDto.getUserPwd());
		reqDto.setUserPwd(encPwd);
		return dao.insert(getDomain("userRegist"), reqDto);
	}
	
	/*
	 * 로그인
	 * request parameter : DTO
	 * result return : HashMap
	 */
	public ResponseEntity<Map<String, Object>> login(reqLoginDto reqDto) throws SQLException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		// 이메일 주소가 유효한지 확인
		Integer emailCrrt = validationEmail(reqDto.getEmailAdr());
		if (emailCrrt == 0) {
			resultMap.put("message", "로그인 실패! 존재하지 않는 이메일 주소입니다.");
			resultMap.put("status", "fail");
			status = HttpStatus.NOT_FOUND;
			logger.error(">>> 존재하지 않는 이메일 주소입니다.");
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		// 암호화되어 DB에 저장되어있는 비밀번호를 먼저 갖고옴
		String encPwd = dao.selectOne(getDomain("selEncPwd"), reqDto);
		// 입력받은 비밀번호와 암호화된 비밀번호가 같은지 비교. (같을시 : True, 다를시 : False)
		boolean pwdValidresult = passwordEncoder.matches(reqDto.getUserPwd(), encPwd);
		try {
			// 비밀번호가 맞다면
			if (pwdValidresult) {
				resLoginDto loginInfo = dao.selectOne(getDomain("login"), reqDto);
				logger.info("로그인 정보 : {}", loginInfo);
				// JWT 데이터 주입 - 회원관리번호, 이름
				String token = jwtService.create(loginInfo);
				resultMap.put("token", token);
				resultMap.put("status", "success");
				status = HttpStatus.ACCEPTED;
				logger.info(">>> 로그인 정보로 생성된 토큰이 브라우저 세션 스토리지에 전송,저장됩니다.");
			// 비밀번호가 틀리다면
			} else {
				resultMap.put("message", "로그인 실패! 비밀번호가 틀립니다.");
				resultMap.put("status", "fail");
				status = HttpStatus.UNAUTHORIZED;
				logger.error(">>> 비밀번호가 틀립니다.");
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			resultMap.put("status", "fail");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error(">>> 로그인 실패!");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 이메일 주소 DB상 존재 여부 확인 (0 : 없음, 1 : 존재)
	private int validationEmail(String emailAdr) {
		return dao.selectOne(getDomain("emailVal"), emailAdr);
	}
	
	
	/*
	 * 회원정보 수정
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userModify(reqUserAccountDto reqDto) throws SQLException{
		Integer userMngtNo = (Integer)dao.selectOne(getDomain("selUserMngtNo"), reqDto);
		reqDto.setUserMngtNo(userMngtNo);
		return dao.update(getDomain("userModify"), reqDto);
	}
	
	/*
	 * 회원탈퇴 (실제데이터를 삭제하는 작업아님)
	 * request parameter : DTO
	 * result return : Integer
	 */
	public int userDelete(reqUserAccountDto reqDto) throws SQLException{
		Integer userMngtNo = (Integer)dao.selectOne(getDomain("selUserMngtNo"), reqDto);
		reqDto.setUserMngtNo(userMngtNo);
		return dao.update(getDomain("userDelete"), reqDto);
	}
	
	// MapperID 도메인 생성 메서드
	private String getDomain(String id) {
		return "com.zipsin.user.".concat(id);
	}
}