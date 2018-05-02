package com.example.dubbo.learntourist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnTouristApplication implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(LearnTouristApplication.class);

    @Value("${machine-number}")
    private String machineNumber;

    public static void main(String[] args) {
        SpringApplication.run(LearnTouristApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("start success machineNumber : {}", machineNumber);
    }
}
