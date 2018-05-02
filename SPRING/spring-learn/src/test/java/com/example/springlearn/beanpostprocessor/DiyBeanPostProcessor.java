//package com.example.springlearn.beanpostprocessor;
//
//import java.beans.PropertyDescriptor;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.PropertyValues;
//import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
//import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Component;
//
///**
// * Created by liuzz on 2018/04/20
// */
//@Component
//public class DiyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {
//
//    /**
//     *
//     */
//
//    @Nullable
//    @Override
//    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        System.out.println("----------------" + beanName + "----------------");
//        System.out.println("1. Bean Post Before Instantiation");
//        return null;
//    }
//
//    @Override
//    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
//        System.out.println("2. Bean Post After Instantiation");
//        return true;
//    }
//
//    @Nullable
//    @Override
//    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
//        System.out.println("3. Bean Post PropertyValues");
//        return pvs;
//    }
//
//    /**
//     *
//     */
//
//    @Nullable
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("4. Bean Post Before Initialization");
//        return bean;
//    }
//
//    @Nullable
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("5. Bean Post After Initialization");
//        return bean;
//    }
//
//    /**
//     *
//     */
//
//    @Override
//    public boolean requiresDestruction(Object bean) {
//        System.out.println("6. requiresDestruction \n");
//        return true;
//    }
//
//    @Override
//    public void postProcessBeforeDestruction(Object o, String s) throws BeansException {
//        System.out.println("-1. Bean Post After Destruction");
//    }
//
//}
