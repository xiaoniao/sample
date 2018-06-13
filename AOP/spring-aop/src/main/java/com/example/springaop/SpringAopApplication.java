package com.example.springaop;

import com.example.springaop.utils.HttpUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Bean
    public HttpUtils httpUtils() {
	    return new HttpUtils();
    }
}
