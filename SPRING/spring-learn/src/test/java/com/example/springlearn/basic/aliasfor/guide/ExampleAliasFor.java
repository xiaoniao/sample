package com.example.springlearn.basic.aliasfor.guide;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

/**
 *
 * 为注解指定别名，使用方式 AnnotatedElementUtils.getMergedAnnotationAttributes。
 */
@Configuration
public class ExampleAliasFor {

    public static void main(String[] args) {
        AnnotationAttributes aa = AnnotatedElementUtils.getMergedAnnotationAttributes(Example.class, ContextConfiguration.class);
        System.out.println("ContextConfiguration : " + aa);

        aa = AnnotatedElementUtils.getMergedAnnotationAttributes(Example.class, XmlTestConfig.class);
        System.out.println("XmlTestConfig : " + aa);

        aa = AnnotatedElementUtils.getMergedAnnotationAttributes(Example.class, MyTestConfig.class);
        System.out.println("MyTestConfig : " + aa);

        aa = AnnotatedElementUtils.getMergedAnnotationAttributes(Example.class, GroovyOrXmlTestConfig.class);
        System.out.println("GroovyOrXmlTestConfig : " + aa);
    }
}
