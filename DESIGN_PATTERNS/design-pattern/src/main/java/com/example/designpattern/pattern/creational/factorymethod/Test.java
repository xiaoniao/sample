package com.example.designpattern.pattern.creational.factorymethod;

/**
 * Created by geely
 *
 * 工厂方法
 *
 * 抽象出一个Video类和一个VideoFactory类
 *
 * 例如 新增Scala的视频
 *      需要增加一个 ScalaVideo 和 ScalaVideoFactory
 */
public class Test {

    public static void main(String[] args) {
        VideoFactory videoFactory = new PythonVideoFactory();
        Video video = videoFactory.getVideo();
        video.record();
    }
}
