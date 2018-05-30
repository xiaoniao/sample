package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STAGE.
 * TP_STAGE
 */
@Mapper
public interface StageDOMapper{

    /**
     * desc:插入表:TP_STAGE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STAGE( ID ,STAGE_NAME ,STAGE_DESC ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{stageName,jdbcType=VARCHAR} , #{stageDesc,jdbcType=VARCHAR} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(StageDO entity);
    /**
     * desc:更新表:TP_STAGE.<br/>
     * descSql =  UPDATE TP_STAGE SET STAGE_NAME = #{stageName,jdbcType=VARCHAR} ,STAGE_DESC = #{stageDesc,jdbcType=VARCHAR} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(StageDO entity);
    /**
     * desc:根据主键删除数据:TP_STAGE.<br/>
     * descSql =  DELETE FROM TP_STAGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STAGE.<br/>
     * descSql =  SELECT * FROM TP_STAGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return StageDO
     */
    StageDO getByPrimary(Integer id);
}
