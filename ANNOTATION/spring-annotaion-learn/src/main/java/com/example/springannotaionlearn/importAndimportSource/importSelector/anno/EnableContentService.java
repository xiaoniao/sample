package com.example.springannotaionlearn.importAndimportSource.importSelector.anno;

import com.example.springannotaionlearn.importAndimportSource.importSelector.beanDefine.ContentImportBeanDefinitionRegistrar;
import com.example.springannotaionlearn.importAndimportSource.importSelector.selector.ContentImportSelector;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Import({DefaultContentConfiguration.class})
@Import({ContentImportSelector.class})
//@Import({ContentImportBeanDefinitionRegistrar.class})
public @interface EnableContentService {

    String policy() default "simple";
}
