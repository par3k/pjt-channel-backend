package com.zipsin.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.zipsin.user.domain.ReqUserAccountDto;

@Mapper
public interface userMapper {

	@Insert("INSERT INTO userBasicInf VALUES ( 0, #{emailAdr}, #{userPwd}, #{userNm}, #{userGender}, #{userBirdD}, 'Y', current_timestamp(3))")
	@Options(useGeneratedKeys = true, keyProperty = "userMngtNo")
	public int save(ReqUserAccountDto reqDto);
	
	@Select("select * from userBasicInf where emailAdr = #{emailAdr} and userstatus = 'Y'")
	public ReqUserAccountDto findOneByEmail(String emailAdr);
	
	@Select("select * from userBasicInf where userMngtNo = #{userMngtNo}")
	public ReqUserAccountDto findOneByRemove(int userMngtNo);
	
	@Select("update userBasicInf SET userStatus = 'N' WHERE userMngtNo = #{userMngtNo}")
	public Long remove(int userMngtNo);
}
