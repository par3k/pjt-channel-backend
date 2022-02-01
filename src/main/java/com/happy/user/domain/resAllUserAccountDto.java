package com.happy.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class resAllUserAccountDto {

	private String userMngtNo;
	
	private String emailAdr;
	
	private String userPwd;
	
	private String userNm;
	
	private String userGender;
	
	private String userBirdD;
	
	
}