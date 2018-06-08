package com.example.masterslavespring;

import com.example.masterslavespring.dal.dataobject.KnowledgeDO;
import com.example.masterslavespring.dal.repository.KnowledgeRepository;
import com.example.masterslavespring.masterslave.DataSource;
import com.example.masterslavespring.masterslave.DataSourceHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzz on 2018/06/08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterSlaveSpringApplication.class)
public class KnowledgeRepositoryTest {

    @Autowired
    KnowledgeRepository knowledgeRepository;

    @Test
    public void testDynamic() {
        testMaster();
        testSlave1();
        testSlave2();
    }

    @Test
    public void testMaster() {
        DataSourceHolder.setDataSource(DataSource.master);
        testQuery();
    }

    @Test
    public void testSlave1() {
        DataSourceHolder.setDataSource(DataSource.slave1);
        testQuery();
    }

    @Test
    public void testSlave2() {
        DataSourceHolder.setDataSource(DataSource.slave2);
        testQuery();
    }

    public void testQuery() {
        Iterable<KnowledgeDO> knowledgeDOS = knowledgeRepository.findAll();
        for (KnowledgeDO knowledgeDO : knowledgeDOS) {
            System.out.println("knowledgeNo : " + knowledgeDO.getKnowledgeNo());
        }
    }

}
