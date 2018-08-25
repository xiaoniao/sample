package com.example.springaopandaspectjlearn.cglib.sample1.lazy;

/**
 * Created by liuzhuang on 6/11/18.
 */
public class PropertyBean {

    private String key;

    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
