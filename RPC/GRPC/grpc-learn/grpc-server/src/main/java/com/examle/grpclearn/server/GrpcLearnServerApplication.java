package com.examle.grpclearn.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcLearnServerApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(GrpcLearnServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }
}