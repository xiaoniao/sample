package com.example.mybatisTest.dao;

import com.example.mybatisTest.model.Activity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
public class ActivityDAO {

    private final SqlSession sqlSession;

    public ActivityDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Activity getById(Integer id) {
        return this.sqlSession.selectOne("com.example.mybatisTest.mapper.ActivityMapper.getById", id); // 单独方法名也可以，但如果方法名重复则必须加上namespace。
    }
}
