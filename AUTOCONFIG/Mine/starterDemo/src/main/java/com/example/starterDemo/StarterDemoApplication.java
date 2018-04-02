package com.example.starterDemo;

import com.example.mineStarter.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.example")
@SpringBootApplication
public class StarterDemoApplication implements CommandLineRunner {

	@Autowired
	private ExampleService exampleService;

	public static void main(String[] args) {
		SpringApplication.run(StarterDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(exampleService.wrap("think"));
	}
}
