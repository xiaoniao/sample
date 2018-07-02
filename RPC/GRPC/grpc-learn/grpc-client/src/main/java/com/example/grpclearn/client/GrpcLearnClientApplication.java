package com.example.grpclearn.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcLearnClientApplication {

    public static void main(String[] args) throws InterruptedException {
        HelloClient client = new HelloClient("localhost", 50051);
        try {
            for (int i = 0; i < 1000; i++) {
                client.greet("刘壮壮" + i);
            }

        } finally {
            client.shutdown();
        }
    }
}