package com.example.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableKafka
public class KafkaProducerApplication implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
	    // 模拟高并发操作
	    for (int i = 0; i < 1000000; i++) {
            kafkaTemplate.send("test-topic", "" + i,"data" + i);
        }
        System.out.println("一百万消息发送完毕");
    }
}