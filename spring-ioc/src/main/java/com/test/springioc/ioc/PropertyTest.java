package com.test.springioc.ioc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzz on 2018/02/05
 */
public class PropertyTest {
    private static Logger log = LoggerFactory.getLogger(PropertyTest.class);


    public static void main(String[] args) {
        showProperty(new BeanDefine());
    }

    private static void showProperty(Object bean) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                log.info("name: {}", propertyDescriptor.getName());
                if (propertyDescriptor.getReadMethod() != null) {
                    log.info("readMethod: {}", propertyDescriptor.getReadMethod().getName());
                }
                if (propertyDescriptor.getWriteMethod() != null) {
                    log.info("writeMethod: {}", propertyDescriptor.getWriteMethod().getName());
                }
                if (propertyDescriptor.getPropertyType() != null) {
                    log.info("propertyTypeClass: {}", propertyDescriptor.getPropertyType().getName());
                }
                log.info("");
            }
            log.info("\n\n**********************************\n\n");
            for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
                log.info("name: {}", methodDescriptor.getName());
                if (methodDescriptor.getMethod() != null) {
                    log.info("method: {}", methodDescriptor.getMethod().getName());
                }
                log.info("");
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
