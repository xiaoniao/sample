package com.example.springannotaionlearn.configurationAndBean;

import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/03
 */
@Component("testComponentBean-component")
public class TestComponentBean {

    private int value;

    public TestComponentBean() {
        System.out.println("TestComponentBean init ......  **** " + this);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void printValue() {
        System.out.println("TestComponentBean value : " + value);
    }


}
