package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudyAssignmentBaseStudyDO;
import com.example.shardingtable.dal.dataobject.StudyAssignmentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDY_ASSIGNMENT.
 * TP_STUDY_ASSIGNMENT
 */
@Mapper
public interface StudyAssignmentBaseStudyDOMapper {

    /**
     * desc:插入表:TP_STUDY_ASSIGNMENT.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDY_ASSIGNMENT( ID ,STUDY_NO ,STUDENT_NO ,STATUS )VALUES( #{id,jdbcType=INTEGER} , #{studyNo,jdbcType=VARCHAR} , #{studentNo,jdbcType=VARCHAR} , #{status,jdbcType=INTEGER} )
     * @param entity entity
     * @return Long
     */
    Long insert(StudyAssignmentBaseStudyDO entity);
    /**
     * desc:根据主键获取数据:TP_STUDY.<br/>
     * descSql =  SELECT * FROM TP_STUDY_ASSIGNMENT WHERE STUDY_NO = #{studyNo,jdbcType=VARCHAR} AND STUDENT_NO = #{studentNo,jdbcType=VARCHAR}
     * @param studyNo studyNo
     * @param studentNo studentNo
     * @return StudyAssignmentDO
     */
    StudyAssignmentBaseStudyDO getByStudentNoAndStudyNo(@Param("studyNo") Long studyNo, @Param("studentNo") Long studentNo);
    /**
     * desc:根据主键获取数据:TP_STUDY.<br/>
     * descSql =  SELECT * FROM TP_STUDY_ASSIGNMENT WHERE STUDENT_NO = #{studentNo,jdbcType=VARCHAR}
     * @param studentNo studentNo
     * @return List<StudyAssignmentDO>
     */
    List<StudyAssignmentBaseStudyDO> listByStudent(Long studentNo);

    List<StudyAssignmentBaseStudyDO> listByStudyNo(Long studyNo);
}
