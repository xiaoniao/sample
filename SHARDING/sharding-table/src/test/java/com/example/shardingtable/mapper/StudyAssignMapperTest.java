package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import com.example.shardingtable.dal.dataobject.StudentDO;
import com.example.shardingtable.dal.dataobject.StudyAssignmentBaseStudyDO;
import com.example.shardingtable.dal.dataobject.StudyAssignmentDO;
import com.example.shardingtable.dal.dataobject.StudyDO;
import com.example.shardingtable.dal.mapper.StudentDOMapper;
import com.example.shardingtable.dal.mapper.StudyAssignmentBaseStudyDOMapper;
import com.example.shardingtable.dal.mapper.StudyAssignmentDOMapper;
import com.example.shardingtable.dal.mapper.StudyDOMapper;
import org.junit.Before;
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
 * Created by liuzz on 2018/06/05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class StudyAssignMapperTest {
    private Logger log = LoggerFactory.getLogger(StudyAssignMapperTest.class);

    @Autowired
    private StudentDOMapper studentDOMapper;

    @Autowired
    private StudyDOMapper studyDOMapper;

    @Autowired
    private StudyAssignmentDOMapper studyAssignmentDOMapper;

    @Autowired
    private StudyAssignmentBaseStudyDOMapper studyAssignmentBaseStudyDOMapper;

    private List<StudentDO> studentDOList = new ArrayList<>();

    private List<StudyDO> studyDOList = new ArrayList<>();

    public void prepare() {
        List<StudentDO> studentDOS = studentDOMapper.listAll();
        studentDOList.addAll(studentDOS.subList(0, 10));
        studentDOList.addAll(studentDOS.subList(studentDOS.size() - 10, studentDOS.size() - 1));

        List<StudyDO> studyDOS = studyDOMapper.listAll();
        studyDOList.addAll(studyDOS.subList(0, 10));
        studyDOList.addAll(studyDOS.subList(studentDOS.size() - 10, studentDOS.size() - 1));
    }

    @Test
    public void testBatchAssignStudy() {
        prepare();
        long startTime = System.currentTimeMillis();
        for (StudentDO studentDO : studentDOList) {
            long time = System.currentTimeMillis();

            for (StudyDO studyDO : studyDOList) {
                // TODO 添加事务

                if (studyAssignmentDOMapper.getByStudentNoAndStudyNo(studyDO.getStudyNo(), studentDO.getStudentNo()) == null) {
                    StudyAssignmentDO studyAssignmentDO = new StudyAssignmentDO();
                    studyAssignmentDO.setStudentNo(studentDO.getStudentNo());
                    studyAssignmentDO.setStudyNo(studyDO.getStudyNo());
                    Long result = studyAssignmentDOMapper.insert(studyAssignmentDO);
                    assetResult(result);
                }

                if (studyAssignmentBaseStudyDOMapper.getByStudentNoAndStudyNo(studyDO.getStudyNo(), studentDO.getStudentNo()) == null) {
                    StudyAssignmentBaseStudyDO studyAssignmentDO = new StudyAssignmentBaseStudyDO();
                    studyAssignmentDO.setStudentNo(studentDO.getStudentNo());
                    studyAssignmentDO.setStudyNo(studyDO.getStudyNo());
                    Long result = studyAssignmentBaseStudyDOMapper.insert(studyAssignmentDO);
                    assetResult(result);
                }
            }
            log.info("指派{}条学习记录，耗时{}", studyDOList.size(), System.currentTimeMillis() - time);
        }
        log.info("指派{}个学员 总耗时", studentDOList.size(), System.currentTimeMillis() - startTime);
    }

    // 查询指派给我的学习
    @Test
    public void testListAssignToMeStudy() {
        List<StudyAssignmentDO> list = studyAssignmentDOMapper.listByStudent(100000008L);
        assert list != null && list.size() > 0;
        for (StudyAssignmentDO studyAssignmentDO : list) {
            log.info("学习{}", studyAssignmentDO.getStudyNo());
        }

        // studyAssignmentBaseStudyDOMapper.listByStudent(100000008L); // 因为不是分区键 会查多个表
    }

    // 查询学习指派给的学员
    @Test
    public void testListStudentByStudyNo() {
        List<StudyAssignmentBaseStudyDO> list = studyAssignmentBaseStudyDOMapper.listByStudyNo(100000987L);
        assert list != null && list.size() > 0;
        for (StudyAssignmentBaseStudyDO studyAssignmentBaseStudyDO : list) {
            log.info("学员{}", studyAssignmentBaseStudyDO.getStudentNo());
        }

        // studyAssignmentDOMapper.listByStudyNo(100000987L); // 因为不是分区键 会查多个表
    }

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }
}
