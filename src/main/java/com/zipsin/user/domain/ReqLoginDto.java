package com.zipsin.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 로그인 요청 DTO
 * @author par3k
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReqLoginDto {
	// 이메일 주소
	private String emailAdr;
	// 유저비밀번호
	private String userPwd;
}
