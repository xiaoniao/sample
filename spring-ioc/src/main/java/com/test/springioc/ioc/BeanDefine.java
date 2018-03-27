package com.test.springioc.ioc;

/**
 * 对公共属性的定义
 *
 * Created by liuzz on 2018/02/05
 */
public class BeanDefine {

    private String id;

    private String className;

    public BeanDefine() {
    }

    public BeanDefine(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
