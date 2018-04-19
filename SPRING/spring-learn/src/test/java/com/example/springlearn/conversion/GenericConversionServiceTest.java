package com.example.springlearn.conversion;

import com.example.springlearn.SpringLearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/04/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class GenericConversionServiceTest {

    @Test
    public void test() {
        GenericConversionService genericConversionService = new GenericConversionService();
        genericConversionService.addConverter(new Converter<String, Integer>() {
            @Nullable
            @Override
            public Integer convert(String s) {
                return Integer.valueOf(s);
            }
        });
        genericConversionService.addConverter(new Converter<String, String>() {
            @Nullable
            @Override
            public String convert(String s) {
                return "※" + s + "※";
            }
        });

        boolean canConvertStringToInteger = genericConversionService.canConvert(String.class, Integer.class);
        System.out.println("canConvertStringToInteger : " + canConvertStringToInteger);
        if (canConvertStringToInteger) {
            Integer result = genericConversionService.convert("1024", Integer.class);
            System.out.println(result);
        }

        boolean canConvertStringToString = genericConversionService.canConvert(String.class, String.class);
        System.out.println("canConvertStringToString : " + canConvertStringToString);
        if (canConvertStringToString) {
            String result = genericConversionService.convert("1024", String.class);
            System.out.println(result);
        }
    }
}
