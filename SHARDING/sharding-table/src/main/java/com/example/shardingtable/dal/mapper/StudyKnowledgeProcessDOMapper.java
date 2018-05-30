package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudyKnowledgeProcessDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDY_KNOWLEDGE_PROCESS.
 * TP_STUDY_KNOWLEDGE_PROCESS
 */
@Mapper
public interface StudyKnowledgeProcessDOMapper{

    /**
     * desc:插入表:TP_STUDY_KNOWLEDGE_PROCESS.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDY_KNOWLEDGE_PROCESS( ID ,STUDY_NO ,STUDENT_NO ,KNOWLEDGE_NO ,FINISH_PERCENT ,STATUS ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{studyNo,jdbcType=VARCHAR} , #{studentNo,jdbcType=VARCHAR} , #{knowledgeNo,jdbcType=VARCHAR} , #{finishPercent,jdbcType=INTEGER} , #{status,jdbcType=CHAR} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(StudyKnowledgeProcessDO entity);
    /**
     * desc:更新表:TP_STUDY_KNOWLEDGE_PROCESS.<br/>
     * descSql =  UPDATE TP_STUDY_KNOWLEDGE_PROCESS SET STUDY_NO = #{studyNo,jdbcType=VARCHAR} ,STUDENT_NO = #{studentNo,jdbcType=VARCHAR} ,KNOWLEDGE_NO = #{knowledgeNo,jdbcType=VARCHAR} ,FINISH_PERCENT = #{finishPercent,jdbcType=INTEGER} ,STATUS = #{status,jdbcType=CHAR} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(StudyKnowledgeProcessDO entity);
    /**
     * desc:根据主键删除数据:TP_STUDY_KNOWLEDGE_PROCESS.<br/>
     * descSql =  DELETE FROM TP_STUDY_KNOWLEDGE_PROCESS WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STUDY_KNOWLEDGE_PROCESS.<br/>
     * descSql =  SELECT * FROM TP_STUDY_KNOWLEDGE_PROCESS WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return StudyKnowledgeProcessDO
     */
    StudyKnowledgeProcessDO getByPrimary(Integer id);
}
