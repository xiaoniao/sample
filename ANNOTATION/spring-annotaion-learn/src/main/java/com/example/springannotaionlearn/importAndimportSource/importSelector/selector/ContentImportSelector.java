package com.example.springannotaionlearn.importAndimportSource.importSelector.selector;

import com.example.springannotaionlearn.importAndimportSource.importSelector.anno.EnableContentService;
import com.example.springannotaionlearn.importAndimportSource.importSelector.config.CoreContentConfiguration;
import com.example.springannotaionlearn.importAndimportSource.importSelector.config.SimpleContentConfiguration;
import java.util.Map;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by liuzz on 2018/04/04
 *
 * 可以读取注解信息，然后返回configuration，来加载Bean
 *
 */
public class ContentImportSelector implements ImportSelector {

    /**
     * 返回 Configuration数组
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableContentService.class.getName(), true);
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(map);

        String policy = attributes.getString("policy");
        if ("core".equals(policy)) {
            return new String[]{CoreContentConfiguration.class.getName()};
        } else {
            return new String[]{SimpleContentConfiguration.class.getName()};
        }
    }
}
