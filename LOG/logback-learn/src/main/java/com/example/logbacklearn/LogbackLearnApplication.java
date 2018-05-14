package com.example.logbacklearn;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogbackLearnApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(LogbackLearnApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LogbackLearnApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info("hello{}", "world");

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        logger.info("loggerContext:{}", loggerContext);
        logger.info("{}", LoggerFactory.getILoggerFactory());
    }
}
