package com.example.dubbo.learntourist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnTouristApplication implements CommandLineRunner {

    @Value("${number}")
    private String number;

    public static void main(String[] args) {
        SpringApplication.run(LearnTouristApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("number : " + number);
    }
}
