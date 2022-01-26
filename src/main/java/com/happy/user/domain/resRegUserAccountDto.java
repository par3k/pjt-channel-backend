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
public class resRegUserAccountDto {

	private String user_mngt_no;
	
	private String emailadr;
	
	private String user_pwd;
	
	private String user_gender;
	
	private String user_bird;
}
