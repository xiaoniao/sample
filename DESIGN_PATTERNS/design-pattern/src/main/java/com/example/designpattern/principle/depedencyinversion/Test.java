package com.example.designpattern.principle.depedencyinversion;

/**
 *
 * 依赖倒置原则，高层模块不应该依赖低层模块，二者都应该依赖其抽象。 扩展的时候增加一个实现了抽象的类
 *
 * 面向接口编程
 *
 * 总结：是通过抽象出了接口，并且把接口的实现选择放在了高层。
 * Created by liuzhuang on 2018/9/23.
 */
public class Test {

    // v1
//    public static void main(String[] args) {
//        Student student = new Student();
//        student.studyJavaCourse();
//        student.studyPythonCourse();
//        // ...
//    }

    // v2 把实现放在高层模块
    public static void main(String[] args) {
        Student student = new Student();
        student.study(new JavaCourse());
        student.study(new PythonCourse());
    }
}
