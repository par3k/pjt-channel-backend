package com.zipsin.common.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/redis")
@Api(tags = {"레디스 테스트 API"})
@RequiredArgsConstructor
public class RedisTestController {

	private final RedisTemplate<String, String> redisTemplate;

	@PostMapping("/redisTest")
	@ApiOperation(value="래디스 키값 입력")
	public ResponseEntity<?> redisConnection() {
		ValueOperations<String, String> vo = redisTemplate.opsForValue();
		vo.set("yellow", "banana");
		vo.set("red", "apple");
		vo.set("green", "watermelon");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/redisTest/{key}")
	@ApiOperation(value="레디스 키 조회")
	public ResponseEntity<?> getRedisLey(@PathVariable String key) {
		ValueOperations<String, String> vo = redisTemplate.opsForValue();
		String value = vo.get(key);
		return new ResponseEntity<>(value, HttpStatus.OK);
	}
}
