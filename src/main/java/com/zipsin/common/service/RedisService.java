package com.zipsin.common.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
	
	private final RedisTemplate<String, String> redisTemplate;
	
	public void setValues(String token, String email) {
		ValueOperations<String, String> vo = redisTemplate.opsForValue();
		vo.set(token, email, Duration.ofMinutes(3));
	}
	
	public String getValues(String token) {
		ValueOperations<String, String> vo = redisTemplate.opsForValue();
		return vo.get(token);
	}
	
	public void delValues(String token) {
		redisTemplate.delete(token.substring(7));
	}
}