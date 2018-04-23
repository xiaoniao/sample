package com.example.springlearn.basic.aliasfor.guide;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * Example: Transitive Implicit Aliases within an Annotation
 *
 *  - In @GroovyOrXmlTestConfig, groovy is an explicit override for the groovyScripts attribute in @MyTestConfig; whereas, xml is an explicit override for the locations attribute in @ContextConfiguration.
 *  - Furthermore, groovy and xml are transitive implicit aliases for each other, since they both effectively override the locations attribute in @ContextConfiguration.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyTestConfig
public @interface GroovyOrXmlTestConfig {

    @AliasFor(annotation = MyTestConfig.class, attribute = "groovyScripts")
    String[] groovy() default {};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] xml() default {};
}