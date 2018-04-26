package com.example.springlearn.propertyeditor;

import com.example.springlearn.model.Goods;
import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/04/26
 */
@Configuration
public class EnableCustomEditorConfigurer {

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();
        customEditors.put(Goods.class, MyGoodsPropertyEditor.class);
        customEditorConfigurer.setCustomEditors(customEditors);
        // customEditorConfigurer.setPropertyEditorRegistrars();
        return customEditorConfigurer;
    }
}
