package com.example.springlearn.basic.aliasfor.guide;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * Example: Implicit Aliases within an Annotation
 *
 *  - In @MyTestConfig, value, groovyScripts, and xmlFiles are all explicit meta-annotation attribute overrides for the locations attribute in @ContextConfiguration.
 *  - These three attributes are therefore also implicit aliases for each other.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration
public @interface MyTestConfig {

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] value() default {};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] groovyScripts() default {};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] xmlFiles() default {};
}
