package com.example.masterslavespring;

import com.example.masterslavespring.dal.dataobject.KnowledgeDO;
import com.example.masterslavespring.dal.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MasterSlaveSpringApplication implements CommandLineRunner {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Iterable<KnowledgeDO> knowledgeDOS = knowledgeRepository.findAll();
        for (KnowledgeDO knowledgeDO : knowledgeDOS) {
            System.out.println("knowledgeNo : " + knowledgeDO.getKnowledgeNo());
        }
    }
}