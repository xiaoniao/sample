package com.example.springlearn.basic.aliasfor.guide;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * Example: Explicit Aliases within an Annotation
 *
 *  - value and locations are explicit aliases for each other.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextConfiguration {

    /**
     * 相互指派别名属性 默认值必须一致
     */

    @AliasFor("locations")
    String[] value() default {};

    @AliasFor("value")
    String[] locations() default {};
}
