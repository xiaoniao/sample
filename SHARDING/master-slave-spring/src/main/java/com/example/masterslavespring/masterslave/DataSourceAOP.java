package com.example.masterslavespring.masterslave;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/06/07
 */
@Aspect
@Component
public class DataSourceAOP {

    @Before("execution(* com.service..*.select*(..)) || execution(* com.service..*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceHolder.setDataSource(DataSource.slave1);
    }

    @Before("execution(* com.service..*.insert*(..)) || execution(* com.service..*.update*(..)) || execution(* com..*.delete*(..)) || execution(* com.service..*.add*(..))")
    public void setWriteDataSourceType() {
        DataSourceHolder.setDataSource(DataSource.master);
    }
}