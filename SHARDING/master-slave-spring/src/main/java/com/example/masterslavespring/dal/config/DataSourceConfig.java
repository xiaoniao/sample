package com.example.masterslavespring.dal.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzz on 2018/06/08
 */

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "customerTransactionManager",
        basePackages = {
                "com.example.masterslavespring.dal.repository"
        }
)
public class DataSourceConfig {

    @Qualifier("dataSourceMaster")
    @Autowired
    private DataSource dataSourceMaster;

    @Qualifier("dataSourceSlave1")
    @Autowired
    private DataSource dataSourceSlave1;

    @Qualifier("dataSourceSlave2")
    @Autowired
    private DataSource dataSourceSlave2;


    @Bean("dataSourceMaster")
    public DataSource dataSourceMaster() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/my_sharding_test2");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    @Bean("dataSourceSlave1")
    public DataSource dataSourceSlave1() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3307/my_sharding_test2");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    @Bean("dataSourceSlave2")
    public DataSource dataSourceSlave2() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3308/my_sharding_test2");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    @Bean
    DynamicDataSource dynamicDataSource(DataSource dataSourceMaster, DataSource dataSourceSlave1, DataSource dataSourceSlave2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSourceMaster", dataSourceMaster);
        targetDataSources.put("dataSourceSlave1", dataSourceSlave1);
        targetDataSources.put("dataSourceSlave2", dataSourceSlave2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
        return dynamicDataSource;
    }

    @Bean("customerEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean customerEntityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dynamicDataSource(dataSourceMaster, dataSourceSlave1, dataSourceSlave2));
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.example.masterslavespring.dal.dataobject");
        return factoryBean;
    }

    @Bean("customerTransactionManager")
    PlatformTransactionManager customerTransactionManager() {
        return new JpaTransactionManager(customerEntityManagerFactory().getObject());
    }
}

