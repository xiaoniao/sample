package com.example.designpattern.pattern.creational.abstractfactory.basicimpl;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;

/**
 * Created by geely
 */
public class PythonVideo extends Video {

    @Override
    public void startPlay() {
        System.out.println("开始播放Python课程视频");
    }
}
