package com.example.springannotaionlearn.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/08
 */
@Component
public class NoLazyBean {

    @Autowired
    private LazyBean lazyBean;

    public NoLazyBean() {
        System.out.println("NoLazyBean init ......");
    }
}
