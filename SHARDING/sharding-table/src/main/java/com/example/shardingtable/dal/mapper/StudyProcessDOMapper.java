package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudyProcessDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDY_PROCESS.
 * TP_STUDY_PROCESS
 */
@Mapper
public interface StudyProcessDOMapper{

    /**
     * desc:插入表:TP_STUDY_PROCESS.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDY_PROCESS( ID ,STUDY_NO ,STUDENT_NO ,FINISH_PERCENT ,STATUS ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{studyNo,jdbcType=VARCHAR} , #{studentNo,jdbcType=VARCHAR} , #{finishPercent,jdbcType=INTEGER} , #{status,jdbcType=CHAR} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(StudyProcessDO entity);
    /**
     * desc:更新表:TP_STUDY_PROCESS.<br/>
     * descSql =  UPDATE TP_STUDY_PROCESS SET STUDY_NO = #{studyNo,jdbcType=VARCHAR} ,STUDENT_NO = #{studentNo,jdbcType=VARCHAR} ,FINISH_PERCENT = #{finishPercent,jdbcType=INTEGER} ,STATUS = #{status,jdbcType=CHAR} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(StudyProcessDO entity);


    Long updateProcess(@Param("studyNo") Long studyNo, @Param("studentNo") Long studentNo, @Param("finishPercent") Integer finishPercent);

    Long updateStatus(@Param("studyNo") Long studyNo, @Param("studentNo") Long studentNo, @Param("status") String status);

    /**
     * desc:根据主键删除数据:TP_STUDY_PROCESS.<br/>
     * descSql =  DELETE FROM TP_STUDY_PROCESS WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STUDY_PROCESS.<br/>
     * descSql =  SELECT * FROM TP_STUDY_PROCESS WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return StudyProcessDO
     */
    StudyProcessDO getByPrimary(Integer id);


    StudyProcessDO getByStudyNoAndStudentNo(@Param("studyNo") Long studyNo, @Param("studentNo") Long studentNo);
}
