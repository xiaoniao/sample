package com.example.starterDemo;

import com.example.mineStarter.ExampleService;
import com.example.third.library.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterDemoApplication implements CommandLineRunner {

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private PayService payService;

    public static void main(String[] args) {
        SpringApplication.run(StarterDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(exampleService.wrap("think"));
        payService.pay();
    }
}