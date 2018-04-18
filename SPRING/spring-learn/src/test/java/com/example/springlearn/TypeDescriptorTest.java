package com.example.springlearn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/04/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class TypeDescriptorTest {

    @Test
    public void test() throws NoSuchFieldException {
        ProductVO productVO = new ProductVO();
        Field field = productVO.getClass().getDeclaredField("name");
        TypeDescriptor typeDescriptor = new TypeDescriptor(field);

        ResolvableType resolvableType = typeDescriptor.getResolvableType();
        System.out.println("resolvableType:" + resolvableType);

        System.out.println("name:" + typeDescriptor.getName());
        System.out.println("type:" + typeDescriptor.getType());
        Annotation[] annotations = typeDescriptor.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation:" + annotation);
        }
        System.out.println("objectType:" + typeDescriptor.getObjectType());
        Field fieldSource = (Field) typeDescriptor.getSource();
        System.out.println("" + fieldSource.getName());
    }
}
