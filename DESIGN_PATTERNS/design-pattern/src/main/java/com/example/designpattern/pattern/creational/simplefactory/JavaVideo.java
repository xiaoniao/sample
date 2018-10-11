package com.example.designpattern.pattern.creational.simplefactory;

/**
 * Created by geely
 */
public class JavaVideo extends Video {

    @Override
    public void produce() {
        System.out.println("录制Java课程视频");
    }
}
