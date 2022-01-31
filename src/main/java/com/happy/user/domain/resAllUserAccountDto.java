package com.happy.user.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class resAllUserAccountDto {

	private String userMngtNo;
	
	private String emailAdr;
	
	private String userPwd;
	
	private String userGender;
	
	private String userBirdD;
}
