package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import com.example.shardingtable.dal.dataobject.KnowledgeDO;
import com.example.shardingtable.dal.dataobject.StudentDO;
import com.example.shardingtable.dal.dataobject.StudyDO;
import com.example.shardingtable.dal.dataobject.StudyStageKnowledgeDO;
import com.example.shardingtable.dal.mapper.KnowledgeDOMapper;
import com.example.shardingtable.dal.mapper.StudyDOMapper;
import com.example.shardingtable.dal.mapper.StudyStageKnowledgeDOMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by liuzz on 2018/06/05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class StudyMapperTest {

    private Logger log = LoggerFactory.getLogger(StudyMapperTest.class);

    private static final int STUDY_COUNT = 1000;
    private static final int STUDY_STAGE_COUNT = 3;
    private static final int STUDY_STAGE_KNOWLEDGE_COUNT = 3;

    @Autowired
    private StudyDOMapper studyDOMapper;

    @Autowired
    private StudyStageKnowledgeDOMapper studyStageKnowledgeDOMapper;

    @Autowired
    private KnowledgeDOMapper knowledgeDOMapper;


    private List<StudentDO> studentDOList;

    private List<KnowledgeDO> knowledgeDOList;


    @Before
    public void prepare() {
        knowledgeDOList = knowledgeDOMapper.listAll();
    }

    public Long queryARandomKnowledgeNo() {
        Random random = new Random();
        int index = random.nextInt(999);
        return knowledgeDOList.get(index).getKnowledgeNo();
    }

    @Test
    public void testAddStudy() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < STUDY_COUNT; i++) {
            StudyDO studyDO = new StudyDO();
            studyDO.setName("study-" + i);
            Long result = studyDOMapper.insert(studyDO);
            assetResult(result);


            for (int stageIndex = 0; stageIndex < STUDY_STAGE_COUNT; stageIndex++) {
                StudyStageKnowledgeDO studyStageKnowledgeDO = new StudyStageKnowledgeDO();
                studyStageKnowledgeDO.setStageName("stage-" + stageIndex);
                studyStageKnowledgeDO.setStudyNo(studyDO.getStudyNo());
                for (int knowledgeIndex = 0; knowledgeIndex < STUDY_STAGE_KNOWLEDGE_COUNT; knowledgeIndex++) {
                    studyStageKnowledgeDO.setKnowledgeNo(queryARandomKnowledgeNo());
                    studyStageKnowledgeDO.setSortNum(stageIndex * STUDY_STAGE_KNOWLEDGE_COUNT + knowledgeIndex);
                    result = studyStageKnowledgeDOMapper.insert(studyStageKnowledgeDO);
                    assetResult(result);
                }
            }
        }
        log.info("插入{}条学习，耗时{}", STUDY_COUNT, System.currentTimeMillis() - time);
    }

    @Test
    public void testListStudy() {
        List<StudyDO> list = studyDOMapper.listAll();
        log.info("学习数量：{}", list.size());
    }

    @Test
    public void testGetStudyInfo() {
        StudyDO studyDO = studyDOMapper.getByStudyNo(100000010L);
        List<StudyStageKnowledgeDO> studyStageKnowledgeDOList = studyStageKnowledgeDOMapper.listByStudyNo(studyDO.getStudyNo());
        List<Long> knowledgeNos = studyStageKnowledgeDOList.stream().map(StudyStageKnowledgeDO::getKnowledgeNo).collect(Collectors.toList());
        List<KnowledgeDO> knowledgeDOList = knowledgeDOMapper.listByKnowledgeNos(knowledgeNos);

        log.info("studyName:{}", studyDO.getName());
        for (KnowledgeDO knowledgeDO : knowledgeDOList) {
            log.info("\t\tknowledgeName:{}", knowledgeDO.getKnowledgeName());
        }
    }

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }
}
