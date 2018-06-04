package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudyStageKnowledgeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDY_STAGE_KNOWLEDGE.
 * TP_STUDY_STAGE_KNOWLEDGE
 */
@Mapper
public interface StudyStageKnowledgeDOMapper{

    /**
     * desc:插入表:TP_STUDY_STAGE_KNOWLEDGE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDY_STAGE_KNOWLEDGE( ID ,STAGE_ID ,KNOWLEDGE_NO ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{stageId,jdbcType=INTEGER} , #{knowledgeNo,jdbcType=VARCHAR} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(StudyStageKnowledgeDO entity);
    /**
     * desc:更新表:TP_STUDY_STAGE_KNOWLEDGE.<br/>
     * descSql =  UPDATE TP_STUDY_STAGE_KNOWLEDGE SET STAGE_ID = #{stageId,jdbcType=INTEGER} ,KNOWLEDGE_NO = #{knowledgeNo,jdbcType=VARCHAR} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(StudyStageKnowledgeDO entity);
    /**
     * desc:根据主键删除数据:TP_STUDY_STAGE_KNOWLEDGE.<br/>
     * descSql =  DELETE FROM TP_STUDY_STAGE_KNOWLEDGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STUDY_STAGE_KNOWLEDGE.<br/>
     * descSql =  SELECT * FROM TP_STUDY_STAGE_KNOWLEDGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return StudyStageKnowledgeDO
     */
    StudyStageKnowledgeDO getByPrimary(Integer id);


    List<StudyStageKnowledgeDO> listByStudyNos(List<Long> list);

    List<StudyStageKnowledgeDO> listByStudyNo(Long studyNo);
}
