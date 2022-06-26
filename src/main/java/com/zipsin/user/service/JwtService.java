package com.zipsin.user.service;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zipsin.user.domain.ResLoginDto;

import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
	
	private String signature = "ACCESSTOKEN";
	private Long expireMin = 20L;
	
	public String create(ResLoginDto resLoginDto) {
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setHeaderParam("typ", "JWT");
		
		jwtBuilder
				.setSubject("토큰")
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
				.claim("user", resLoginDto)
				.claim("greeting", "환영합니다!" + resLoginDto.getUserNm() + "님 ~ ^*^");
		
		jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());
		String jwt = jwtBuilder.compact();
		logger.info("JWT 토큰 : {}", jwt);
		return jwt;
	}
	
	public void chkValid(String jwt) {
		Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
	}
	
	public Map<String, Object> get(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		logger.info("claims : {}", claims);
		return claims.getBody();
	}
}
