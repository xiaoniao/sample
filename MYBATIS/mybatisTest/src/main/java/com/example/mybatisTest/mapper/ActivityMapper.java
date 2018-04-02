package com.example.mybatisTest.mapper;

import com.example.mybatisTest.model.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface ActivityMapper {

    @Select("SELECT * FROM op_activity where id = #{id}")
    Activity getById(Integer id);

    /**
     * 默认是找同包名下的**Mapper.xml
     * @param status
     * @return
     */
    Activity getByStatus(@Param("status") Integer status);
}