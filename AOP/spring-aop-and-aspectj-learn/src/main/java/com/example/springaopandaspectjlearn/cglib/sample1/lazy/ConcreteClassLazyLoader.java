package com.example.springaopandaspectjlearn.cglib.sample1.lazy;

import com.example.springaopandaspectjlearn.cglib.sample1.callback.TargetObject;
import net.sf.cglib.proxy.LazyLoader;

/**
 * Created by liuzhuang on 6/11/18.
 */
public class ConcreteClassLazyLoader implements LazyLoader {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("before lazyLoader...");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("key");
        propertyBean.setValue(new TargetObject());
        System.out.println("after lazyLoader...");
        return propertyBean;
    }
}
