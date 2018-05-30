package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudyStageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDY_STAGE.
 * TP_STUDY_STAGE
 */
@Mapper
public interface StudyStageDOMapper{

    /**
     * desc:插入表:TP_STUDY_STAGE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDY_STAGE( ID ,STUDY_NO ,STAGE_ID ,SORT_NUM ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{studyNo,jdbcType=VARCHAR} , #{stageId,jdbcType=INTEGER} , #{sortNum,jdbcType=INTEGER} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(StudyStageDO entity);
    /**
     * desc:更新表:TP_STUDY_STAGE.<br/>
     * descSql =  UPDATE TP_STUDY_STAGE SET STUDY_NO = #{studyNo,jdbcType=VARCHAR} ,STAGE_ID = #{stageId,jdbcType=INTEGER} ,SORT_NUM = #{sortNum,jdbcType=INTEGER} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(StudyStageDO entity);
    /**
     * desc:根据主键删除数据:TP_STUDY_STAGE.<br/>
     * descSql =  DELETE FROM TP_STUDY_STAGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STUDY_STAGE.<br/>
     * descSql =  SELECT * FROM TP_STUDY_STAGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return StudyStageDO
     */
    StudyStageDO getByPrimary(Integer id);
}
