package com.example.c3p0Sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * C3p0 :
 *      开源的JDBC连接池，实现了数据源和JNDI绑定，支持JDBC3规范和JDBC2的标准扩展。
 *      目前使用它的开源项目有Hibernate、Spring等。
 *      单线程，性能较差，适用于小型系统，代码600KB左右。
 */
@SpringBootApplication
public class C3p0SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(C3p0SampleApplication.class, args);
    }
}
