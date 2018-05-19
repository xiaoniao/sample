package com.example.springretrylearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringRetryLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRetryLearnApplication.class, args);
	}
}
