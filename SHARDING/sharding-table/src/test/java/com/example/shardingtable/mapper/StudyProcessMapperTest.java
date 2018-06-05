package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import com.example.shardingtable.dal.dataobject.StudyAssignmentDO;
import com.example.shardingtable.dal.dataobject.StudyKnowledgeProcessDO;
import com.example.shardingtable.dal.dataobject.StudyProcessDO;
import com.example.shardingtable.dal.dataobject.StudyStageKnowledgeDO;
import com.example.shardingtable.dal.mapper.StudyAssignmentDOMapper;
import com.example.shardingtable.dal.mapper.StudyKnowledgeProcessDOMapper;
import com.example.shardingtable.dal.mapper.StudyProcessDOMapper;
import com.example.shardingtable.dal.mapper.StudyStageKnowledgeDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liuzz on 2018/05/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class StudyProcessMapperTest {
    private Logger log = LoggerFactory.getLogger(StudyProcessMapperTest.class);

    @Autowired
    private StudyStageKnowledgeDOMapper studyStageKnowledgeDOMapper;

    @Autowired
    private StudyAssignmentDOMapper studyAssignmentDOMapper;

    @Autowired
    private StudyProcessDOMapper studyProcessDOMapper;

    @Autowired
    private StudyKnowledgeProcessDOMapper studyKnowledgeProcessDOMapper;

    @Test
    public void testUpdateStudyProcess() {
        Long studentNo = 100000002L;
        studentNo = 100000997L;
        List<StudyAssignmentDO> studyAssignmentDOList = studyAssignmentDOMapper.listByStudent(studentNo);
        List<Long> studyNoList = studyAssignmentDOList.stream().map(StudyAssignmentDO::getStudyNo).collect(Collectors.toList());

        for (Long studyNo : studyNoList) {
            List<StudyStageKnowledgeDO> list = studyStageKnowledgeDOMapper.listByStudyNo(studyNo);

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

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }

}
