package com.zipsin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.zipsin.user.service.JwtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	
	private final JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info(request.getMethod() + " : " + request.getServletPath());
		logger.info(">>> 인터셉터 들어옴");
		
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		} else {
			String token = request.getHeader("token");
			if (token != null && token.length() > 0) {
				logger.info(">>> 토큰 유효성 체크...");
				jwtService.chkValid(token);
				logger.info("사용 가능 : {}", token);
				return true;
			} else {
				throw new RuntimeException("인증 토큰이 없습니다.");
			}
		}
	}
	
}
