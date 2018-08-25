package com.test.springioc.ioc;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzz on 2018/02/05
 */
public class MyClassPathXMLApplicationContext {

    private Logger log = LoggerFactory.getLogger(MyClassPathXMLApplicationContext.class);

    private List<BeanDefine> beanList = new ArrayList<>();

    private Map<String, Object> singletons = new HashMap<>();



    public MyClassPathXMLApplicationContext(String fileName) {
        this.readXML(fileName);

        this.instancesBean();

        this.annotationInject();
    }

    /**
     * 读取Bean配置文件
     */
    @SuppressWarnings("unchecked")
    public void readXML(String fileName) {
        Document document;
        SAXReader saxReader = new SAXReader();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            document = saxReader.read(classLoader.getResourceAsStream(fileName));
            Element beans = document.getRootElement();
            for (Iterator<Element> beansList = beans.elementIterator(); beansList.hasNext(); ) {
                Element element = beansList.next();
                BeanDefine bean = new BeanDefine(
                        element.attributeValue("id"),
                        element.attributeValue("class"));
                beanList.add(bean);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            log.info("读取配置文件出错....");
        }
    }

    /**
     * 实例化Bean
     */
    public void instancesBean() {
        for (BeanDefine bean : beanList) {
            try {
                singletons.put(bean.getId(), Class.forName(bean.getClassName()).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
                log.info("实例化Bean出错...");
            }
        }
    }

    /**
     * 注解处理器
     * 如果注解 AutoResource 配置了name属性，则根据name所指定的名称获取要注入的实例引用，
     * 如果注解 AutoResource 没有配置name属性，则根据属性所属类型来扫描配置文件获取要注入的实例引用
     */
    public void annotationInject() {
        for (String beanName : singletons.keySet()) {
            Object bean = singletons.get(beanName);
            if (bean != null) {
                methodAnnotation(bean);
                fieldAnnotation(bean);
            }
        }
    }

    /**
     * 处理在set方法加入的注解
     *
     * @param bean 处理的bean
     */
    public void methodAnnotation(Object bean) {
        try {
            // 获取其属性的描述
            PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : ps) {
                // 获取所有set方法
                Method setter = propertyDescriptor.getWriteMethod();
                // 判断set方法是否定义了注解
                if (setter != null && setter.isAnnotationPresent(AutoResource.class)) {
                    // 获取当前注解，并判断name属性是否为空
                    AutoResource resource = setter.getAnnotation(AutoResource.class);
                    String name;
                    Object value = null;
                    if (!"".equals(resource.name())) {
                        // 获取注解的name属性的内容
                        name = resource.name();
                        value = singletons.get(name);
                    } else {
                        // 如果当前注解没有指定name属性,则根据类型进行匹配
                        for (String key : singletons.keySet()) {
                            // 判断当前属性所属的类型是否在配置文件中存在
                            if (propertyDescriptor.getPropertyType().isAssignableFrom(singletons.get(key).getClass())) {
                                // 获取类型匹配的实例对象
                                value = singletons.get(key);
                                break;
                            }
                        }
                    }
                    log.info("value:{}", value);
                    // 允许访问private方法
                    setter.setAccessible(true);
                    // 把引用对象注入属性
                    setter.invoke(bean, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("set方法注解解析异常..........");
        }
    }

    /**
     * 处理在字段上的注解
     *
     * @param bean 处理的bean
     */
    public void fieldAnnotation(Object bean) {
        try {
            // 获取其全部的字段描述
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f != null && f.isAnnotationPresent(AutoResource.class)) {
                    AutoResource resource = f.getAnnotation(AutoResource.class);
                    String name;
                    Object value = null;
                    if (!"".equals(resource.name())) {
                        name = resource.name();
                        value = singletons.get(name);
                    } else {
                        for (String key : singletons.keySet()) {
                            // 判断当前属性所属的类型是否在配置文件中存在
                            if (f.getType().isAssignableFrom(singletons.get(key).getClass())) {
                                // 获取类型匹配的实例对象
                                value = singletons.get(key);
                                break;
                            }
                        }
                    }
                    // 允许访问private字段
                    f.setAccessible(true);
                    // 把引用对象注入属性
                    f.set(bean, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("字段注解解析异常..........");
        }
    }

    /**
     * 获取Map中的对应的bean实例
     */
    public Object getBean(String beanId) {
        return singletons.get(beanId);
    }
}
