package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import com.example.shardingtable.dal.dataobject.*;
import com.example.shardingtable.dal.mapper.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * Created by liuzz on 2018/05/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class StudyDOMapperShardingTest {
    private Logger log = LoggerFactory.getLogger(StudyDOMapperShardingTest.class);

    private static final int STUDENT_COUNT = 1000;
    private static final int KNOWLEDGE_COUNT = 1000;
    private static final int STUDY_COUNT = 1000;
    private static final int STUDY_STAGE_COUNT = 3;
    private static final int STUDY_STAGE_KNOWLEDGE_COUNT = 3;


    @Autowired
    private StudentDOMapper studentDOMapper;

    @Autowired
    private StudyDOMapper studyDOMapper;

    @Autowired
    private KnowledgeDOMapper knowledgeDOMapper;

    @Autowired
    private StudyStageKnowledgeDOMapper studyStageKnowledgeDOMapper;

    @Autowired
    private StudyAssignmentDOMapper studyAssignmentDOMapper;

    @Autowired
    private StudyProcessDOMapper studyProcessDOMapper;

    @Autowired
    private StudyKnowledgeProcessDOMapper studyKnowledgeProcessDOMapper;

    @Autowired
    private StudyStageKnowledgeDOMapper stageKnowledgeDOMapper;


    private List<StudentDO> studentDOList;

    private List<KnowledgeDO> knowledgeDOList;

    private List<StudyDO> studyDOList;


    public Long queryARandomKnowledgeNo() {
        Random random = new Random();
        int index = random.nextInt(999);
        return knowledgeDOList.get(index).getKnowledgeNo();
    }

    @Test
    public void multiThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100 * 10);
        for (int j = 0; j < 100; j++) {
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    testAddStudent();
                    testAddKnowledge();
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }

    /******************************************************************************************************************/

    @Before
    public void prepareBasicData() {
        knowledgeDOList = knowledgeDOMapper.listAll();
        if (CollectionUtils.isEmpty(knowledgeDOList)) {
            testAddKnowledge();
            knowledgeDOList = knowledgeDOMapper.listAll();
        }

        studentDOList = studentDOMapper.listAll();
        if (CollectionUtils.isEmpty(studentDOList)) {
            testAddStudent();
            studentDOList = studentDOMapper.listAll();
        }

        studyDOList = studyDOMapper.listAll();
        if (CollectionUtils.isEmpty(studyDOList)) {
            testBatchAddStudy();
            studyDOList = studyDOMapper.listAll();
        }
    }

    /******************************************************************************************************************/

    /**
     * 按照学员编号进行分区
     */
    @Test
    public void testAddStudent() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < STUDENT_COUNT; i++) {
            StudentDO studentDO = new StudentDO();
            studentDO.setName("name" + i);
            Long result = studentDOMapper.insert(studentDO);
            assetResult(result);
        }
        log.info("插入{}条学员记录，耗时{}", STUDENT_COUNT, System.currentTimeMillis() - time);
    }

    /**
     * 按照资源编号进行分区
     */
    @Test
    public void testAddKnowledge() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < KNOWLEDGE_COUNT; i++) {
            KnowledgeDO knowledgeDO = new KnowledgeDO();
            knowledgeDO.setKnowledgeName("name" + i);
            Long result = knowledgeDOMapper.insert(knowledgeDO);
            assetResult(result);
        }
        log.info("插入{}条资源记录，耗时{}", KNOWLEDGE_COUNT, System.currentTimeMillis() - time);
    }

    /**
     * 按学习编号进行分区
     */
    @Test
    public void testBatchAddStudy() {
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
                    studyStageKnowledgeDO.setSortNum(stageIndex + knowledgeIndex);
                    result = studyStageKnowledgeDOMapper.insert(studyStageKnowledgeDO);
                    assetResult(result);
                }
            }
        }
        log.info("插入{}条学习，耗时{}", STUDY_COUNT, System.currentTimeMillis() - time);
    }

    /******************************************************************************************************************/

    /**
     * 按学员编号进行分区
     */
    /**
     * 按学习编号进行分区
     */
    @Test
    public void testBatchAssignStudy() {
        long startTime = System.currentTimeMillis();
        studentDOList.stream().parallel().forEach((studentDO) -> {
            long time = System.currentTimeMillis();
            studyDOList.stream().parallel().forEach((studyDO -> {
                if (studyAssignmentDOMapper.getByStudentNoAndStudyNo(studyDO.getStudyNo(), studentDO.getStudentNo()) == null) {
                    StudyAssignmentDO studyAssignmentDO = new StudyAssignmentDO();
                    studyAssignmentDO.setStudentNo(studentDO.getStudentNo());
                    studyAssignmentDO.setStudyNo(studyDO.getStudyNo());
                    Long result = studyAssignmentDOMapper.insert(studyAssignmentDO);
                    assetResult(result);
                }
            }));
            log.info("指派{}条学习记录，耗时{}", studyDOList.size(), System.currentTimeMillis() - time);
        });
        log.info("指派{}个学员 总耗时", studentDOList.size(), System.currentTimeMillis() - startTime);
    }

    @Test
    public void testGetStudy() {
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int index = random.nextInt(999);
            StudentDO studentDO  = studentDOList.get(index);
            List<StudyAssignmentDO> studyAssignmentDOList = studyAssignmentDOMapper.listByStudent(studentDO.getStudentNo());
            for (StudyAssignmentDO studyAssignmentDO : studyAssignmentDOList) {
                Long studyNo = studyAssignmentDO.getStudyNo();

                StudyDO studyDO = studyDOMapper.getByStudyNo(studyNo);

                List<StudyStageKnowledgeDO> studyStageKnowledgeDOList = stageKnowledgeDOMapper.listByStudyNo(studyNo);

                List<Long> knowledgeNos = studyStageKnowledgeDOList.stream().map(StudyStageKnowledgeDO::getKnowledgeNo).collect(Collectors.toList());
                List<KnowledgeDO> knowledgeDOList = knowledgeDOMapper.listByKnowledgeNos(knowledgeNos);
                log.info("------------ studyName:{}", studyDO.getName());
                for (KnowledgeDO knowledgeDO : knowledgeDOList) {
                    log.info("------------ knowledgeName:{}", knowledgeDO.getKnowledgeName());
                }
            }
        }
    }

    @Test
    public void testUpdateProcess() {
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int index = random.nextInt(999);
            StudentDO studentDO  = studentDOList.get(index);
            Long studentNo = studentDO.getStudentNo();
            List<StudyAssignmentDO> studyAssignmentDOList = studyAssignmentDOMapper.listByStudent(studentNo);
            List<Long> studyNoList = studyAssignmentDOList.stream().map(StudyAssignmentDO::getStudyNo).collect(Collectors.toList());
            for (Long studyNo : studyNoList) {

                List<StudyStageKnowledgeDO> list = stageKnowledgeDOMapper.listByStudyNo(studyNo);

                for (StudyStageKnowledgeDO studyStageKnowledgeDO : list) {

                    Long knowledgeNo = studyStageKnowledgeDO.getKnowledgeNo();
                    if (studyKnowledgeProcessDOMapper.getByStudyNoAndStudentNoAndKnowledgeNo(studyNo, studentNo, knowledgeNo) == null) {
                        StudyKnowledgeProcessDO studyKnowledgeProcessDO = new StudyKnowledgeProcessDO();
                        studyKnowledgeProcessDO.setStudyNo(studyNo);
                        studyKnowledgeProcessDO.setStudentNo(studentNo);
                        studyKnowledgeProcessDO.setKnowledgeNo(knowledgeNo);
                        studyKnowledgeProcessDOMapper.insert(studyKnowledgeProcessDO);
                    }
                    studyKnowledgeProcessDOMapper.updateProcess(studyNo, studentNo, knowledgeNo, 100);
                    studyKnowledgeProcessDOMapper.updateStatus(studyNo, studentNo, knowledgeNo, "1");
                }

                if (studyProcessDOMapper.getByStudyNoAndStudentNo(studyNo, studentNo) == null) {
                    StudyProcessDO studyProcessDO = new StudyProcessDO();
                    studyProcessDO.setStudyNo(studyNo);
                    studyProcessDO.setStudentNo(studentNo);
                    studyProcessDOMapper.insert(studyProcessDO);
                }
                studyProcessDOMapper.updateProcess(studyNo, studentNo, 100);
                studyProcessDOMapper.updateStatus(studyNo, studentNo, "1");
            }
        }
    }

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }

}
