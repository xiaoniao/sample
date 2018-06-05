package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.KnowledgeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_KNOWLEDGE.
 * TP_KNOWLEDGE
 */
@Mapper
public interface KnowledgeDOMapper{

    /**
     * desc:插入表:TP_KNOWLEDGE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_KNOWLEDGE( ID ,KNOWLEDGE_NO ,KNOWLEDGE_NAME ,KNOWLEDGE_IMG ,KNOWLEDGE_STATUS ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{knowledgeNo,jdbcType=VARCHAR} , #{knowledgeName,jdbcType=VARCHAR} , #{knowledgeImg,jdbcType=LONGVARCHAR} , #{knowledgeStatus,jdbcType=CHAR} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(KnowledgeDO entity);
    /**
     * desc:更新表:TP_KNOWLEDGE.<br/>
     * descSql =  UPDATE TP_KNOWLEDGE SET KNOWLEDGE_NO = #{knowledgeNo,jdbcType=VARCHAR} ,KNOWLEDGE_NAME = #{knowledgeName,jdbcType=VARCHAR} ,KNOWLEDGE_IMG = #{knowledgeImg,jdbcType=LONGVARCHAR} ,KNOWLEDGE_STATUS = #{knowledgeStatus,jdbcType=CHAR} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(KnowledgeDO entity);
    /**
     * desc:根据主键删除数据:TP_KNOWLEDGE.<br/>
     * descSql =  DELETE FROM TP_KNOWLEDGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_KNOWLEDGE.<br/>
     * descSql =  SELECT * FROM TP_KNOWLEDGE WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return KnowledgeDO
     */
    KnowledgeDO getByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_KNOWLEDGE.<br/>
     * descSql =  SELECT * FROM TP_KNOWLEDGE
     * @return List<KnowledgeDO>
     */
    List<KnowledgeDO> listAll();

    List<KnowledgeDO> listByKnowledgeNos(List<Long> list);

    List<KnowledgeDO> listByBetween(@Param("start")Long start, @Param("end") Long end);

    List<KnowledgeDO> listByBetweenAnd(@Param("start")Long start, @Param("end") Long end);
}
