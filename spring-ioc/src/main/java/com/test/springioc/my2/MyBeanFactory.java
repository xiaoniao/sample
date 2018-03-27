package com.test.springioc.my2;

/**
 * Created by liuzz on 2018/02/05
 */
public interface MyBeanFactory {

    /**
     * 根据name返回bean
     * @param name
     * @return
     */
    Object getBean(String name);
}
