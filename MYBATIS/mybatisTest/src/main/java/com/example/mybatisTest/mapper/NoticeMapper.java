package com.example.mybatisTest.mapper;

import com.example.mybatisTest.model.Notice;
import org.apache.ibatis.annotations.Select;

public interface NoticeMapper {

    @Select("select * from op_notice where id = #{id}")
    Notice getById(Integer id);
}
