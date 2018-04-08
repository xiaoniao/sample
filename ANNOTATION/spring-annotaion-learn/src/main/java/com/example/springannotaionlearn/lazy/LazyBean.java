package com.example.springannotaionlearn.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/08
 */
@Lazy
@Component
public class LazyBean {

    public LazyBean() {
        System.out.println("LazyBean init ......");
    }
}
