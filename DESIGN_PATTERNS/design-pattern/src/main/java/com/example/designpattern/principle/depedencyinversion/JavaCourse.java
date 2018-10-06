package com.example.designpattern.principle.depedencyinversion;

/**
 * Created by liuzhuang on 2018/9/23.
 */
public class JavaCourse implements Course {

    @Override
    public void studyCourse() {
        System.out.println("study Java");
    }
}
