package com.example.shardingtable.dal.mapper;

import com.example.shardingtable.dal.dataobject.StudentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table TP_STUDENT.
 * TP_STUDENT
 */
@Mapper
public interface StudentDOMapper {

    /**
     * desc:插入表:TP_STUDENT.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO TP_STUDENT( ID ,STUDENT_NO ,NAME ,MEMO ,USER_NO ,JOB ,DEPART ,COMPANY ,STATUS ,DATE_CREATED ,LAST_UPDATED )VALUES( #{id,jdbcType=INTEGER} , #{studentNo,jdbcType=VARCHAR} , #{name,jdbcType=VARCHAR} , #{memo,jdbcType=VARCHAR} , #{userNo,jdbcType=VARCHAR} , #{job,jdbcType=VARCHAR} , #{depart,jdbcType=VARCHAR} , #{company,jdbcType=VARCHAR} , #{status,jdbcType=TINYINT} , now() , now() )
     *
     * @param entity entity
     * @return Long
     */
    Long insert(StudentDO entity);

    /**
     * desc:更新表:TP_STUDENT.<br/>
     * descSql =  UPDATE TP_STUDENT SET STUDENT_NO = #{studentNo,jdbcType=VARCHAR} ,NAME = #{name,jdbcType=VARCHAR} ,MEMO = #{memo,jdbcType=VARCHAR} ,USER_NO = #{userNo,jdbcType=VARCHAR} ,JOB = #{job,jdbcType=VARCHAR} ,DEPART = #{depart,jdbcType=VARCHAR} ,COMPANY = #{company,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=TINYINT} ,LAST_UPDATED = now() WHERE ID = #{id,jdbcType=INTEGER}
     *
     * @param entity entity
     * @return Long
     */
    Long update(StudentDO entity);

    /**
     * desc:根据主键删除数据:TP_STUDENT.<br/>
     * descSql =  DELETE FROM TP_STUDENT WHERE ID = #{id,jdbcType=INTEGER}
     *
     * @param id id
     * @return Long
     */
    Long deleteByPrimary(Integer id);

    /**
     * desc:根据主键获取数据:TP_STUDENT.<br/>
     * descSql =  SELECT * FROM TP_STUDENT WHERE ID = #{id,jdbcType=INTEGER}
     *
     * @param id id
     * @return StudentDO
     */
    StudentDO getByPrimary(Integer id);

    List<StudentDO> listAll();
}
