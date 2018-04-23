package com.example.springlearn.basic.aliasfor.guide;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * Example: Explicit Alias for Attribute in Meta-annotation
 *
 *  - In @XmlTestConfig, xmlFiles is an explicit alias for locations in @ContextConfiguration.
 *  - In other words, xmlFiles overrides the locations attribute in @ContextConfiguration.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration
public @interface XmlTestConfig {

    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] xmlFiles();
}
