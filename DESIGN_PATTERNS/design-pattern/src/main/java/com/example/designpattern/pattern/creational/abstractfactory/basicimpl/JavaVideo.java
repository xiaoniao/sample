package com.example.designpattern.pattern.creational.abstractfactory.basicimpl;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;

/**
 * Created by geely
 */
public class JavaVideo extends Video {

    @Override
    public void startPlay() {
        System.out.println("开始播放视频");
    }
}
