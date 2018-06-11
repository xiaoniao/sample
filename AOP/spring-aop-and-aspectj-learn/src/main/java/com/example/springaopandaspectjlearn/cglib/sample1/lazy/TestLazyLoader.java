package com.example.springaopandaspectjlearn.cglib.sample1.lazy;

/**
 * Created by liuzhuang on 6/11/18.
 */
public class TestLazyLoader {

    public static void main(String[] args) {
        LazyBean lazyBean = new LazyBean();
        PropertyBean propertyBean = lazyBean.getPropertyBean();

        // PropertyBean$$EnhancerByCGLB$$d2116eb3@600
        System.out.println("-----------------" + propertyBean);
        System.out.println(propertyBean.getKey() + " - " + propertyBean.getValue());

        // PropertyBean$$EnhancerByCGLB$$e9785d23@601
        PropertyBean dispatcherPropertyBean = lazyBean.getPropertyBeanDispatcher();
        System.out.println("-----------------" + dispatcherPropertyBean);
        System.out.println(dispatcherPropertyBean.getKey() + " - " + dispatcherPropertyBean.getValue());
    }
}
