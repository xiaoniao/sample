package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudyDO;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDY.
 * TP_STUDY
 */
@Mapper
public interface StudyDOMapper{

    /**
     * desc:插入表:TP_STUDY.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDY( ID ,STUDY_NO ,NAME ,STATUS ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{studyNo,jdbcType=VARCHAR} , #{name,jdbcType=VARCHAR} , #{status,jdbcType=CHAR} , now() , now() )
     * @param entity entity
     * @return Long
     */
    Long insert(StudyDO entity);
    /**
     * desc:更新表:TP_STUDY.<br/>
     * descSql =  UPDATE TP_STUDY SET STUDY_NO = #{studyNo,jdbcType=VARCHAR} ,NAME = #{name,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=CHAR} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long update(StudyDO entity);
    /**
     * desc:根据主键删除数据:TP_STUDY.<br/>
     * descSql =  DELETE FROM TP_STUDY WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STUDY.<br/>
     * descSql =  SELECT * FROM TP_STUDY WHERE ID = #{id,jdbcType=INTEGER}
     * @param id id
     * @return StudyDO
     */
    StudyDO getByPrimary(Integer id);
    /**
     * desc:根据主键获取数据:TP_STUDY.<br/>
     * descSql =  SELECT * FROM TP_STUDY WHERE STUDY_NO = #{studyNo,jdbcType=VARCHAR}
     * @param studyNo studyNo
     * @return StudyDO
     */
    StudyDO getByStudyNo(String studyNo);
    /**
     * desc:根据主键获取数据:TP_STUDY.<br/>
     * descSql =  SELECT * FROM TP_STUDY
     * @return List<StudyDO>
     */
    List<StudyDO> listAll();
}
