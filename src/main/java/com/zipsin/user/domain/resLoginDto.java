package com.zipsin.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

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
	// 유저관리번호
	private String userMngtNo;
	// 유저명
	private String userNm;
}