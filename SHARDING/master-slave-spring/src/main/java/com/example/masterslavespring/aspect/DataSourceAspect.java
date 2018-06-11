package com.example.masterslavespring.aspect;

import com.example.masterslavespring.masterslave.DataSource;
import com.example.masterslavespring.masterslave.DataSourceHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/06/07
 */
@Aspect
@Component
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class DataSourceAspect {
    private Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("execution(* com.example.masterslavespring.dal.repository..*.select*(..)) || " +
            "execution(* com.example.masterslavespring.dal.repository..*.get*(..))    || " +
            "execution(* com.example.masterslavespring.dal.repository..*.find*(..))       ")
    public void setReadDataSourceType() {
        log.info("---------------- setReadDataSourceType ----------------");
        DataSourceHolder.setDataSource(DataSource.slave1);
    }

//    @Before("execution(* com.example.masterslavespring.dal.repository.*.*(..))")
//    public void setReadDataSourceType2() {
//        log.info("----------------setReadDataSourceType");
//        DataSourceHolder.setDataSource(DataSource.slave1);
//    }

    @Before("execution(* com.example.masterslavespring.dal.repository..*.insert*(..)) || execution(* com.example.masterslavespring.dal.repository..*.update*(..)) || execution(* com.example.masterslavespring.dal.repository..*.delete*(..)) || execution(* com.example.masterslavespring.dal.repository..*.add*(..))")
    public void setWriteDataSourceType() {
        log.info("----------------setWriteDataSourceType ----------------");
        DataSourceHolder.setDataSource(DataSource.master);
    }

}