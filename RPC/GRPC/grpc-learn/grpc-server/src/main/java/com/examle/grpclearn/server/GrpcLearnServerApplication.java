package com.examle.grpclearn.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcLearnServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }
}