package com.zipsin.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zipsin.user.domain.ReqUserAccountDto;

// JUnit4
@RunWith(SpringRunner.class)
@SpringBootTest
public class userRegistTest {

	@Autowired
	private userMapper userMapper;
	
	@Test
	@DisplayName("회원가입 테스트")
	public void registTest() throws Exception {
		// given - 이런조건에서
		ReqUserAccountDto user = new ReqUserAccountDto(0, "test2@junit4.com",
				"1q2w3e4r", "테스트", "Z", "999999");
		
		// when - 이런 동작을 진행하면
		userMapper.save(user);
		
		// then - 이런 결과가 발생한다
		ReqUserAccountDto findUser = userMapper.findOneByEmail(user.getEmailAdr());
		Assertions.assertThat(user.getEmailAdr()).isEqualTo(findUser.getEmailAdr());
		
	}
	
	@Test
	@DisplayName("회원가입 후 탈회 처리")
	public void deleteTest() throws Exception {
		// given - 이런조건에서
		ReqUserAccountDto user = new ReqUserAccountDto(0, "test2@junit4.com",
				"1q2w3e4r", "테스트", "Z", "999999");
		
		// when - 이런 동작을 진행하면
		ReqUserAccountDto findUser = userMapper.findOneByEmail(user.getEmailAdr());
		userMapper.remove(findUser.getUserMngtNo());
		
		// then - 이런 결과가 발생한다
		ReqUserAccountDto resultRemoveUser = userMapper.findOneByRemove(findUser.getUserMngtNo());
		Assertions.assertThat("N").isEqualTo(resultRemoveUser.getUserstatus());
		
	}

}
