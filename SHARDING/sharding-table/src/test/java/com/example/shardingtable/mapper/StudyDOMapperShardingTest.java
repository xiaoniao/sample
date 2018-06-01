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

    @Autowired
    private StudentDOMapper studentDOMapper;

    @Autowired
    private StudyDOMapper studyDOMapper;

    @Autowired
    private KnowledgeDOMapper knowledgeDOMapper;

    @Autowired
    private StageDOMapper stageDOMapper;

    @Autowired
    private StudyStageDOMapper studyStageDOMapper;

    @Autowired
    private StudyStageKnowledgeDOMapper studyStageKnowledgeDOMapper;

    @Autowired
    private StudyAssignmentDOMapper studyAssignmentDOMapper;

    @Autowired
    private StudyProcessDOMapper studyProcessDOMapper;

    @Autowired
    private StudyKnowledgeProcessDOMapper studyKnowledgeProcessDOMapper;

    private List<StudentDO> studentDOList;

    private List<KnowledgeDO> knowledgeDOList;

    private List<StudyDO> studyDOList;

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }

    @Before
    public void queryAll() {
        knowledgeDOList = knowledgeDOMapper.listAll();
        if (CollectionUtils.isEmpty(knowledgeDOList)) {
            testAddKnowledge();
        }

        studentDOList = studentDOMapper.listAll();
        if (CollectionUtils.isEmpty(studentDOList)) {
            testAddStudent();
        }

        studyDOList = studyDOMapper.listAll();
        if (CollectionUtils.isEmpty(studyDOList)) {
            testAddStudy();
        }
    }

    public Long getRandomKnowledgeNo() {
        Random random = new Random();
        int randomNum = random.nextInt(999);
        return knowledgeDOList.get(randomNum).getKnowledgeNo();
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

    @Test
    public void testAddStudent() {
        for (int i = 0; i < 10; i++) {
            StudentDO studentDO = new StudentDO();
            studentDO.setName("name" + i);
            Long result = studentDOMapper.insert(studentDO);
            assetResult(result);
        }
    }

    /**
     * 然使用了自增主键，在sql中就不应该在写id，道理与mysql的autoincrement相同。 因为作者实现逻辑和日常使用逻辑相冲突只能妥协
     */
    @Test
    public void testAddKnowledge() {
        for (int i = 0; i < 1000; i++) {
            KnowledgeDO knowledgeDO = new KnowledgeDO();
            Long result = knowledgeDOMapper.insert(knowledgeDO);
            assetResult(result);
        }
    }


    @Test
    public void testAddStudy() {
        for (int i = 0; i < 1000; i++) {
            StudyDO studyDO = new StudyDO();
            studyDO.setName("study-" + i);
            Long result = studyDOMapper.insert(studyDO);
            assetResult(result);

            for (int j = 0; j < 3; j++) {
                StageDO stageDO = new StageDO();
                stageDO.setStageName("stage-" + j);
                result = stageDOMapper.insert(stageDO);
                assetResult(result);

                StudyStageDO studyStageDO = new StudyStageDO();
                studyStageDO.setStageId(stageDO.getId());
                studyStageDO.setStudyNo(studyDO.getStudyNo());
                result = studyStageDOMapper.insert(studyStageDO);
                assetResult(result);

                for (int k = 0; k < 3; k++) {
                    StudyStageKnowledgeDO studyStageKnowledgeDO = new StudyStageKnowledgeDO();
                    studyStageKnowledgeDO.setStageId(stageDO.getId());
                    studyStageKnowledgeDO.setKnowledgeNo(getRandomKnowledgeNo());
                    result = studyStageKnowledgeDOMapper.insert(studyStageKnowledgeDO);
                    assetResult(result);
                }
            }
        }
    }

    @Test
    public void testAssignStudent() {
        for (StudentDO studentDO : studentDOList) {
            for (StudyDO studyDO : studyDOList) {
                if (studyAssignmentDOMapper.getByStudentNoAndStudyNo(studyDO.getStudyNo(), studentDO.getStudentNo()) != null) {
                    continue;
                }
                StudyAssignmentDO studyAssignmentDO = new StudyAssignmentDO();
                studyAssignmentDO.setStudentNo(studentDO.getStudentNo());
                studyAssignmentDO.setStudyNo(studyDO.getStudyNo());
                Long result = studyAssignmentDOMapper.insert(studyAssignmentDO);
                assetResult(result);
            }
        }
    }

    @Test
    public void testGetStudy() {
        for (StudentDO studentDO : studentDOList) {
            Long studentNo = studentDO.getStudentNo();
            List<StudyAssignmentDO> studyAssignmentDOList = studyAssignmentDOMapper.listByStudent(studentNo);
            for (StudyAssignmentDO studyAssignmentDO : studyAssignmentDOList) {
                Long studyNo = studyAssignmentDO.getStudyNo();
                StudyDO studyDO = studyDOMapper.getByStudyNo(studyNo);
                log.info("------------ studyName:{}", studyDO.getName());
            }
        }
    }

    @Test
    public void testUpdateProcess() {
        for (StudentDO studentDO : studentDOList) {
            Long studentNo = studentDO.getStudentNo();
            List<StudyAssignmentDO> studyAssignmentDOList = studyAssignmentDOMapper.listByStudent(studentNo);
            List<Long> studyNoList = studyAssignmentDOList.stream().map(StudyAssignmentDO::getStudyNo).collect(Collectors.toList());
            for (Long studyNo : studyNoList) {


                studyStageKnowledgeDOMapper.


                studyProcessDOMapper.updateProcess(studyNo, studentNo, 100);
                studyProcessDOMapper.updateStatus(studyNo, studentNo, "1");
                studyKnowledgeProcessDOMapper.updateProcess(studyNo, studentNo, 100);
                studyKnowledgeProcessDOMapper.updateStatus(studyNo, studentNo, "1");
            }
        }
    }
}
