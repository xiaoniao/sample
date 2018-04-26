package com.example.springlearn.propertyeditor;

import com.example.springlearn.SpringLearnApplication;
import java.beans.PropertyEditor;
import java.net.URL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * Support
 *      - n. 支持，维持；支援，供养；支持者，支撑物
 *      - vt. 支持，支撑，支援；扶持，帮助；赡养，供养
 *
 *
 * - java beans 中定义的方法
 * PropertyEditor
 * PropertyEditorSupport
 *
 * - spring beans 转换数据
 * StringArrayPropertyEditor、URLEditor、ResourceEditor
 *
 *
 * TypeConverter
 *
 * PropertyEditorRegistry
 * PropertyEditorRegistrySupport
 *  - (Map<Class<?>, PropertyEditor>) defaultEditors, overriddenDefaultEditor, (Map<String, PropertyEditorRegistrySupport.CustomEditorHolder>) customEditorsForPath
 *
 * TypeConverterSupport
 *  - TypeConverterDelegate
 *          - 如果找不到对应类的editor 则交由ConversionService 处理
 *          - 代理模式
 * SimpleTypeConverter
 *
 *
 *
 *
 * TypeConverter -> String 转换成其他类型
 * Converter/GenericConverter -> 其他类型转换成其他类型
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class PropertyEditorTest {

    @Value("${url.baidu}")
    private URL baiduUrl;

    /**
     * debug
     */
    @Test
    public void testURLEditor() {
        // debug
        System.out.println(baiduUrl);
    }

    /**
     * 测试PropertyEditor 实现类
     */
    @Test
    public void testPropertyEditorImpl() {
        StringArrayPropertyEditor propertyEditor = new StringArrayPropertyEditor();
        propertyEditor.setAsText("1,2,3,4");

        Object value = propertyEditor.getValue();
        if (value instanceof String[]) {
            String[] list = (String[]) value;
            for (String str : list) {
                System.out.println(str);
            }
        }
    }

    /**
     * 测试PropertyEditorRegistry 实现类
     */
    @Test
    public void testPropertyEditorRegistrySupport() {
        PropertyEditorRegistrySupport propertyEditorRegistrySupport = new PropertyEditorRegistrySupport();
        PropertyEditor propertyEditor = propertyEditorRegistrySupport.getDefaultEditor(Boolean.class);
        System.out.println("defaultPropertyEditor: " + propertyEditor); // 默认不使用defaultEditor

        propertyEditorRegistrySupport.overrideDefaultEditor(Boolean.class, new CustomBooleanEditor(true));

        propertyEditorRegistrySupport.registerCustomEditor(Boolean.class, "hello", new CustomBooleanEditor(true));
        boolean hasCustomerEditor = propertyEditorRegistrySupport.hasCustomEditorForElement(Boolean.class, "hello");
        System.out.println("hasCustomerEditor: " + hasCustomerEditor);

        PropertyEditor customerPropertyEditor = propertyEditorRegistrySupport.findCustomEditor(Boolean.class, "hello");
        System.out.println("customerPropertyEditor: " + customerPropertyEditor);

        propertyEditorRegistrySupport.useConfigValueEditors();
    }

    @Test
    public void testSimpleTypeConverter() {
        SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
        Boolean result = simpleTypeConverter.convertIfNecessary("1", Boolean.class);
        System.out.println(result);
    }
}
