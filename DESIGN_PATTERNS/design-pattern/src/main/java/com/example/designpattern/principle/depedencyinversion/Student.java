package com.example.designpattern.principle.depedencyinversion;

/**
 * Created by liuzhuang on 2018/9/23.
 */
public class Student {

    public void study(Course course) {
        course.studyCourse();
    }


    // v1 具体实现在低层模块
    public void studyJavaCourse() {
        System.out.println("study Java");
    }

    public void studyPythonCourse() {
        System.out.println("study Python");
    }
}
