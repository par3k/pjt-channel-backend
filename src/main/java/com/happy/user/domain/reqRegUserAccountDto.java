package com.happy.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;


/**
 * 회원가입 요청 DTO
 * @author alex
 *
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class reqRegUserAccountDto {

	private String userMngtNo;
	
	private String emailAdr;
	
	private String userPwd;
	
	private String userGender;
	
	private String userBirdD;

}
