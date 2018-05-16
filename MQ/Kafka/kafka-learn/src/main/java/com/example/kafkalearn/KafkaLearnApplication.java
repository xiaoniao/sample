package com.example.kafkalearn;

import com.example.kafkalearn.boot.BootJavaSample;
import com.example.kafkalearn.plain.PlainJavaSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaLearnApplication implements CommandLineRunner {

    @Autowired
    private PlainJavaSample plainJavaSample;

//    @Autowired
//    private ConfigJavaSample configJavaSample;

    @Autowired
    private BootJavaSample bootJavaSample;

    public static void main(String[] args) {
        SpringApplication.run(KafkaLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // plainJavaSample.testAutoCommit();
        // configJavaSample.testSimple();
        bootJavaSample.sendMsg();
    }
}
