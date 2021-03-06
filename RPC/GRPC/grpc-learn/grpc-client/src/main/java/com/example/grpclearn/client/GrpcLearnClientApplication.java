package com.example.grpclearn.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcLearnClientApplication implements CommandLineRunner {
    private static Logger log = LoggerFactory.getLogger(GrpcLearnClientApplication.class);

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(GrpcLearnClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        HelloClient client = new HelloClient("localhost", 50051);
        try {

            for (int i = 0; i < 1; i++) {
                long time = System.currentTimeMillis();
//                client.greet("刘壮壮" + i);
//                //Client streaming RPCs
//                client.greeting();
//                client.getInfos();
//                //Server streaming RPCs
//                client.getPeoplesByName();
//                //Bidirectional streaming RPCs
//                client.getStudentsList();
//                client.getUserById();
                client.testAsynsOrSync();
                log.info("耗时：{}", (System.currentTimeMillis() - time));
            }
        } finally {
            // client.shutdown();
        }
    }
}