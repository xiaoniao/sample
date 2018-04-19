package com.example.springlearn.environment;

import java.util.Map;
import org.junit.Test;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

/**
 * Environment 做 properties 读取和 profiles 配置
 *
 *
 * xx.properties 数据保存在PropertySource中
 *
 * Created by liuzz on 2018/04/19
 */
public class StandardEnvironmentTest {

    @Test
    public void testPropertySources() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();
        MutablePropertySources mutablePropertySources = standardEnvironment.getPropertySources();
        for (PropertySource<?> propertySource : mutablePropertySources) {
            System.out.println("--------------" + propertySource.getName() + "---------------");

            /**
             * 属性键值对保存在PropertySource的 source 中
             */
            Map map = (Map) propertySource.getSource();
            for (Object key : map.keySet()) {
                System.out.println("" + key + " : " + map.get(key));
            }
        }
    }

    @Test
    public void testSystem() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();

        System.out.println("----------------- 1. systemEnvironmentMap ----------------------");
        Map<String, Object> systemEnvironmentMap = standardEnvironment.getSystemEnvironment();
        for (Object key : systemEnvironmentMap.keySet()) {
            System.out.println("" + key + " : " + systemEnvironmentMap.get(key));
        }

        System.out.println("----------------- 2. systemPropertiesMap ----------------------");
        Map<String, Object> systemPropertiesMap = standardEnvironment.getSystemProperties();
        for (Object key : systemPropertiesMap.keySet()) {
            System.out.println("" + key + " : " + systemPropertiesMap.get(key));
        }
    }

    @Test
    public void testProfiles() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();
        System.out.println("----------------- 3. activeProfiles ----------------------");
        String[] activeProfiles = standardEnvironment.getActiveProfiles();
        for (String profile : activeProfiles) {
            System.out.println("activeProfile : " + profile);
        }

        System.out.println("----------------- 4. defaultProfiles ----------------------");
        String[] defaultProfiles = standardEnvironment.getDefaultProfiles();
        for (String profile : defaultProfiles) {
            System.out.println("defaultProfile : " + profile);
        }
    }

    @Test
    public void testProperties() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();

        System.out.println("----------------- 5. property ----------------------");
        System.out.println(standardEnvironment.getProperty("HOME"));

        System.out.println(standardEnvironment.getProperty("ages"));
    }

    @Test
    public void testConvert() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();

        ConfigurableConversionService configurableConversionService = standardEnvironment.getConversionService();

        assert configurableConversionService != null;
    }
}
