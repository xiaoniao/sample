package com.test.springioc.my2;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by liuzz on 2018/02/05
 */
public class TestConfigurationManager {

    public static void main(String[] args) {
        Map<String, Bean> beanConfig = ConfigurationManager.getBeanConfig("/applicationAnnotation.xml");
        for (Entry<String, Bean> e : beanConfig.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }
}
