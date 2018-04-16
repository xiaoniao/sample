package com.example.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class SpringAnnotaionLearnApplication implements CommandLineRunner {

    @Value("${love}")
    private String love;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringAnnotaionLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(love);
        System.out.println(environment.resolvePlaceholders("${love}"));
        System.out.println(environment.resolveRequiredPlaceholders("${love}"));
        System.out.println(environment.getProperty("${love}"));
    }
}
