package com.example.log4j2learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Log4j2LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Log4j2LearnApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        LogUtils.printCurrentLog();

    }
}
