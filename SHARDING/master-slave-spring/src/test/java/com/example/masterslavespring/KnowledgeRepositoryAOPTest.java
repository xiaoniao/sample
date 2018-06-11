package com.example.masterslavespring;

import com.example.masterslavespring.dal.dataobject.KnowledgeDO;
import com.example.masterslavespring.dal.repository.KnowledgeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by liuzz on 2018/06/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterSlaveSpringApplication.class)
public class KnowledgeRepositoryAOPTest {

    @Autowired
    KnowledgeRepository knowledgeRepository;

    @Test
    public void testQuery() {
        Iterable<KnowledgeDO> knowledgeDOS = knowledgeRepository.findAll();
        for (KnowledgeDO knowledgeDO : knowledgeDOS) {
            System.out.println("knowledgeNo : " + knowledgeDO.getKnowledgeNo());
        }
    }

    @Test
    public void testFindByKnowledgeNo() {
        List<KnowledgeDO> knowledgeDOs = knowledgeRepository.findByKnowledgeName("11");
        for (KnowledgeDO knowledgeDO : knowledgeDOs) {
            System.out.println("knowledgeNo : " + knowledgeDO.getKnowledgeNo());
        }
    }
}
