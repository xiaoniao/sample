package com.example.JVMlearn.annotations;

import java.lang.reflect.Method;

/**
 * Created by liuzhuang on 2018/8/21.
 */
public class AnnotationTest {

    @AnnotationSource("bb-source")
    public void f1() {

    }

    @AnnotationClass("bb-class")
    public void f2() {

    }

    @AnnotationRuntime("bb-runtime")
    public void f3() {

    }


    public static void main(String[] args) throws NoSuchMethodException {

        AnnotationTest annotationTest = new AnnotationTest();

        Method f1 = annotationTest.getClass().getMethod("f1", null);
        Method f2 = annotationTest.getClass().getMethod("f2", null);
        Method f3 = annotationTest.getClass().getMethod("f3", null);


        AnnotationSource annotationSource = f1.getAnnotation(AnnotationSource.class);
        AnnotationClass annotationClass = f2.getAnnotation(AnnotationClass.class);
        AnnotationRuntime annotationRuntime = f3.getAnnotation(AnnotationRuntime.class);

        System.out.println(annotationSource);
        System.out.println(annotationClass);
        System.out.println(annotationRuntime);
    }
}
