package com.example.rpcLearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan()
@ServletComponentScan
public class RpcLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcLearnApplication.class, args);
	}
}
