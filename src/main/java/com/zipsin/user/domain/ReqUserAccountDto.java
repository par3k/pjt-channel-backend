package com.zipsin.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 회원가입, 회원정보수정, 회원탈퇴 요청 DTO
 * @author par3k
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReqUserAccountDto {
	// 회원고유번호
	private Integer userMngtNo;
	// 이메일 주소
	private String emailAdr;
	// 유저비밀번호
	private String userPwd;
	// 유저명
	private String userNm;
	// 유저성별
	private String userGender;
	// 유저생년월일
	private String userBirdD;
	// 유저상태
	private String userstatus;
	
	public ReqUserAccountDto(Integer userMngtNo, String emailAdr, String userPwd, String userNm, String userGender,
			String userBirdD) {
		super();
		this.userMngtNo = userMngtNo;
		this.emailAdr = emailAdr;
		this.userPwd = userPwd;
		this.userNm = userNm;
		this.userGender = userGender;
		this.userBirdD = userBirdD;
	}
	
	
}