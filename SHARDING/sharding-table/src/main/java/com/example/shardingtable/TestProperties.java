package com.example.shardingtable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/06/01
 */
@ConfigurationProperties(prefix = "sharding.jdbc.config.sharding")
@Component
public class TestProperties {

    private String defaultKeyGeneratorClassName;

    private String defaultKeyGeneratorClass;

    public String getDefaultKeyGeneratorClassName() {
        return defaultKeyGeneratorClassName;
    }

    public void setDefaultKeyGeneratorClassName(String defaultKeyGeneratorClassName) {
        this.defaultKeyGeneratorClassName = defaultKeyGeneratorClassName;
    }
}
