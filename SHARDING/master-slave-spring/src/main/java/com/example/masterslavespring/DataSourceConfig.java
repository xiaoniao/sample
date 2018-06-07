//package com.example.masterslavespring;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by liuzz on 2018/06/07
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Bean("dataSourceMaster")
//    public DataSource dataSourceMaster() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/my_sharding_test2");
//        config.setUsername("root");
//        config.setPassword("123456");
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        return new HikariDataSource(config);
//    }
//
//    @Bean("dataSourceSlave1")
//    public DataSource dataSourceSlave1() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3307/my_sharding_test2");
//        config.setUsername("root");
//        config.setPassword("123456");
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        return new HikariDataSource(config);
//    }
//
//    @Bean("dataSourceSlave2")
//    public DataSource dataSourceSlave2() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3308/my_sharding_test2");
//        config.setUsername("root");
//        config.setPassword("123456");
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        return new HikariDataSource(config);
//    }
//
//    @Bean
//    public DynamicDataSource dynamicDataSource(DataSource dataSourceMaster, DataSource dataSourceSlave1, DataSource dataSourceSlave2) {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("dataSourceMaster", dataSourceMaster);
//        targetDataSources.put("dataSourceSlave1", dataSourceSlave1);
//        targetDataSources.put("dataSourceSlave2", dataSourceSlave2);
//        dynamicDataSource.setTargetDataSources(targetDataSources);
//        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
//        return dynamicDataSource;
//    }
//}
