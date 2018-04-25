package com.example.springlearn.propertyeditor;

import org.junit.Test;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;

/**
 *
 * Support
 *      - n. 支持，维持；支援，供养；支持者，支撑物
 *      - vt. 支持，支撑，支援；扶持，帮助；赡养，供养
 *
 *
 *
 *
 *
 * TypeConverter
 *
 * PropertyEditorRegistry
 * PropertyEditorRegistrySupport
 * TypeConverterSupport
 * SimpleTypeConverter
 *
 * PropertyEditor
 */
public class PropertyEditorTest {


    @Test
    public void testPropertyEditor() {

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
}
