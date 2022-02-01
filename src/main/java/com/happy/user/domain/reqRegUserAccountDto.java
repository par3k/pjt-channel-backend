package com.happy.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 회원가입 요청 DTO
 * @author alex
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class reqRegUserAccountDto {

	private String userMngtNo;
	
	private String emailAdr;
	
	private String userPwd;
	
	private String userNm;
	
	private String userGender;
	
	private String userBirdD;

}