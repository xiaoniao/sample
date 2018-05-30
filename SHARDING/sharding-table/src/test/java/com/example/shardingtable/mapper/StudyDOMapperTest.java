package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import com.example.shardingtable.dal.dataobject.*;
import com.example.shardingtable.dal.mapper.*;
import com.example.shardingtable.utils.CodeUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;

/**
 * Created by liuzz on 2018/05/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class StudyDOMapperTest {

    @Autowired
    StudentDOMapper studentDOMapper;

    @Autowired
    StudyDOMapper studyDOMapper;

    @Autowired
    KnowledgeDOMapper knowledgeDOMapper;

    @Autowired
    StageDOMapper stageDOMapper;

    @Autowired
    StudyStageDOMapper studyStageDOMapper;

    @Autowired
    StudyStageKnowledgeDOMapper studyStageKnowledgeDOMapper;

    @Autowired
    StudyAssignmentDOMapper studyAssignmentDOMapper;

    List<StudentDO> studentDOList;

    List<KnowledgeDO> knowledgeDOList;

    List<StudyDO> studyDOList;

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }

    @Before
    public void queryAll() {
        knowledgeDOList = knowledgeDOMapper.listAll();
        studentDOList = studentDOMapper.listAll();
        studyDOList = studyDOMapper.listAll();
    }

    public String getRandomKnowledgeNo() {
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        return knowledgeDOList.get(randomNum).getKnowledgeNo();
    }

    @Test
    public void testAddStudent() {
        for (int i = 0; i < 1000; i++) {
            StudentDO studentDO = new StudentDO();
            studentDO.setName("name" + i);
            studentDO.setStudentNo(CodeUtil.getCodeByHead("STU"));
            Long result = studentDOMapper.insert(studentDO);
            assetResult(result);
        }
    }

    @Test
    public void testAddKnowledge() {
        for (int i = 0; i < 1000; i++) {
            KnowledgeDO knowledgeDO = new KnowledgeDO();
            knowledgeDO.setKnowledgeNo(CodeUtil.getCodeByHead("KNO"));
            Long result = knowledgeDOMapper.insert(knowledgeDO);
            assetResult(result);
        }
    }

    @Test
    public void testAddStudy() {
        for (int i = 0; i < 1000; i++) {
            StudyDO studyDO = new StudyDO();
            studyDO.setStudyNo(CodeUtil.getCodeByHead("STY"));
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
        for (int i = 0; i < 10; i++) {
            StudentDO studentDO = studentDOList.get(i);
            String studentNo = studentDO.getStudentNo();
            List<StudyAssignmentDO> studyAssignmentDOList = studyAssignmentDOMapper.listByStudent(studentNo);
            for (StudyAssignmentDO studyAssignmentDO : studyAssignmentDOList) {
                String studyNo = studyAssignmentDO.getStudyNo();
                StudyDO studyDO = studyDOMapper.getByStudyNo(studyNo);
            }
        }
    }

    @Test
    public void testUpdateProcess() {

    }
}
