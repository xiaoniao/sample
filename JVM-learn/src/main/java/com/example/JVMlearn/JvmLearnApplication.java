package com.example.JVMlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JvmLearnApplication {

	PersonBuilder personBuilder;

	public static void main(String[] args) {
		SpringApplication.run(JvmLearnApplication.class, args);
	}
}
