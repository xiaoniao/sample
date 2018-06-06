package com.example.masterslave;

import com.example.masterslave.dal.dataobject.KnowledgeDO;
import com.example.masterslave.dal.mapper.KnowledgeDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzz on 2018/06/06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MasterSlaveApplication.class)
public class KnowledgeMapperTest {
    private Logger log = LoggerFactory.getLogger(KnowledgeMapperTest.class);

    private static final int KNOWLEDGE_COUNT = 1000;

    @Autowired
    private KnowledgeDOMapper knowledgeDOMapper;

    @Test
    public void testAddKnowledge() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < KNOWLEDGE_COUNT; i++) {
            KnowledgeDO knowledgeDO = new KnowledgeDO();
            knowledgeDO.setKnowledgeNo((long) i);
            knowledgeDO.setKnowledgeName("name" + i);
            Long result = knowledgeDOMapper.insert(knowledgeDO);
            assetResult(result);
        }
        log.info("插入{}条资源记录，耗时{}", KNOWLEDGE_COUNT, System.currentTimeMillis() - time);
    }

    @Test
    public void testListKnowledge() {
        List<KnowledgeDO> knowledgeDOList = knowledgeDOMapper.listAll();
        log.info("资源数量：{}", knowledgeDOList.size());
    }

    @Test
    public void testListByKnowledgeNos() {
        List<Long> knowledgeNos = new ArrayList<>();
        knowledgeNos.add(100000001L);
        knowledgeNos.add(100000002L);
        List<KnowledgeDO> knowledgeDOList = knowledgeDOMapper.listByKnowledgeNos(knowledgeNos);
        log.info("资源数量：{}", knowledgeDOList.size());
    }

    @Test
    public void testListBetween() {
        List<KnowledgeDO> knowledgeDOList = knowledgeDOMapper.listByBetween(100000001L, 100000010L);
        log.info("资源数量：{}", knowledgeDOList.size());
    }

    @Test
    public void testListBetweenAND() {
        // DefaultRangeShardingAlgorithm
        List<KnowledgeDO> knowledgeDOList = knowledgeDOMapper.listByBetweenAnd(100000001L, 100000010L);
        log.info("资源数量：{}", knowledgeDOList.size());
    }

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }
}
