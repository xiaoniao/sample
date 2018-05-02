package com.example.c3p0Sample;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/05/02
 */
@Configuration
public class C3p0Configuration {

    @Bean
    public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://10.0.31.151:3306/easybao-gateway?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&rewriteBatchedStatements=TRUE");
        cpds.setUser("root");
        cpds.setPassword("123456");
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setCheckoutTimeout(1000);
        cpds.setMaxStatements(0);
        return cpds;
    }
}
