package com.zipsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class zipsinApplication {

	public static void main(String[] args) {
		SpringApplication.run(zipsinApplication.class, args);
	}

}
