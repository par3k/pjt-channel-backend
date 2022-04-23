package com.zipsin.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 로그인 응답DTO
 * @author par3k
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class resLoginDto {
	// 이메일 주소
	private String emailAdr;
	// 유저명
	private String userNm;
	// 유저성별
	private String userGender;
	// 유저생년월일
	private String userBirdD;
	
}