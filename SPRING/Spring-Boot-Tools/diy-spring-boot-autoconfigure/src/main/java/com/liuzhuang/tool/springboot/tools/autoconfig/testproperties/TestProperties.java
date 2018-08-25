package com.liuzhuang.tool.springboot.tools.autoconfig.testproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liuzhuang on 2018/8/24.
 */
@ConfigurationProperties("com.test")
public class TestProperties {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
