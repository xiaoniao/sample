package com.example.mybatisTest;

import com.example.mybatisTest.dao.ActivityDAO;
import com.example.mybatisTest.mapper.ActivityMapper;
import com.example.mybatisTest.mapper.NoticeMapper;
import com.example.mybatisTest.model.Activity;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@MapperScan(value = "com.example.mybatisTest.mapper")
@SpringBootApplication
public class MybatisTestApplication implements CommandLineRunner {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private ActivityDAO activityDAO;

    /**
     * DataSourceAutoConfiguration
     */

    public static void main(String[] args) {
        SpringApplication.run(MybatisTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--------- Activity Mapper getById");
        System.out.println(activityMapper.getById(1));

        System.out.println("--------- Notice Mapper getById");
        System.out.println(noticeMapper.getById(3));

        System.out.println("--------- Activity DAO getById");
        System.out.println(activityDAO.getById(1));

        System.out.println("--------- Activity Mapper XML getById");
        System.out.println(activityMapper.getByStatus(1));
    }

    /**
     * 个性化设置Mybatis属性 [Configuration]
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {

        };
    }
}
