package com.example.springaopandaspectjlearn.cglib.sample1.lazy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by liuzhuang on 6/11/18.
 */
public class LazyBean {

    private PropertyBean propertyBean;
    private PropertyBean propertyBeanDispatcher;

    public LazyBean() {
        this.propertyBean = createPropertyBean();
        this.propertyBeanDispatcher = createPropertyBeanDispatcher();
    }

    private PropertyBean createPropertyBean() {
        return (PropertyBean) Enhancer.create(PropertyBean.class, new ConcreteClassLazyLoader());
    }

    private PropertyBean createPropertyBeanDispatcher() {
        return (PropertyBean) Enhancer.create(PropertyBean.class, new ConcreteClassDispatcher());
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }

    public PropertyBean getPropertyBeanDispatcher() {
        return propertyBeanDispatcher;
    }

    public void setPropertyBeanDispatcher(PropertyBean propertyBeanDispatcher) {
        this.propertyBeanDispatcher = propertyBeanDispatcher;
    }
}
