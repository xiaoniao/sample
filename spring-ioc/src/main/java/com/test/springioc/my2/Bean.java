package com.test.springioc.my2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzz on 2018/02/05
 */
public class Bean {

    public static final String SINGLETON = "singleton";
    public static final String PROTOTYPE = "prototype";

    // 默认创建的bean对象设置成是单例的
    private String scope = SINGLETON;

    private String name;
    private String className;
    private List<Property> properties = new ArrayList<Property>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Bean [name=" + name + ", className=" + className + ", scope=" + scope + ", properties=" + properties
                + "]";
    }
}
