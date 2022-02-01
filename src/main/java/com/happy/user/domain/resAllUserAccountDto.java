package com.happy.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 전체회원 조회정보 응답DTO
 * @author alex
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class resAllUserAccountDto {

	private String userMngtNo;
	
	private String emailAdr;
	
	private String userPwd;
	
	private String userNm;
	
	private String userGender;
	
	private String userBirdD;
	
	
}