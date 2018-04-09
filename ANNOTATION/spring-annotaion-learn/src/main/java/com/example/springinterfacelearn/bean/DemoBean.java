package com.example.springinterfacelearn.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/08
 */
@Component
public class DemoBean implements InitializingBean, DisposableBean {

    /**
     * 初始化BEAN后
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet .......");
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy ......");
    }


}
