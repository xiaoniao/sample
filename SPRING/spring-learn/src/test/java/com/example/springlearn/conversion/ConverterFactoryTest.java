package com.example.springlearn.conversion;

import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by liuzz on 2018/04/19
 */
public class ConverterFactoryTest {

    public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

        /**
         * 增加了泛型
         */
        @Override
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return new StringToEnum(getEnumType(targetType));
        }

        /**
         * 来实现不同枚举的统一转换入口
         */
        private class StringToEnum<T extends Enum> implements Converter<String, T> {

            private final Class<T> enumType;

            public StringToEnum(Class<T> enumType) {
                this.enumType = enumType;
            }

            @Override
            public T convert(String source) {
                if (source.length() == 0) {
                    // It's an empty enum identifier: reset the enum value to null.
                    return null;
                }
                return (T) Enum.valueOf(this.enumType, source.trim());
            }
        }

        public Class<?> getEnumType(Class<?> targetType) {
            Class<?> enumType = targetType;
            while (enumType != null && !enumType.isEnum()) {
                enumType = enumType.getSuperclass();
            }
            if (enumType == null) {
                throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
            }
            return enumType;
        }

    }

    /**
     * 枚举
     */
    public enum A {

        TEST(1, "");

        Integer value;
        String name;

        A(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    public enum B {

        TEST(2, "");

        Integer value;
        String name;

        B(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void testStringToEnumConverterFactory() {
        StringToEnumConverterFactory s = new StringToEnumConverterFactory();
        A a = s.getConverter(A.class).convert("TEST");
        System.out.println(a.getValue());

        B b = s.getConverter(B.class).convert("TEST");
        System.out.println(b.getValue());
    }
}
