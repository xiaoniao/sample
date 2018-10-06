package com.example.designpattern.pattern.creational.abstractfactory.factory;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;
import com.example.designpattern.pattern.creational.abstractfactory.basicimpl.JavaArticle;
import com.example.designpattern.pattern.creational.abstractfactory.basicimpl.JavaVideo;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;

/**
 * Created by geely
 */
public class JavaCourseFactory implements CourseFactory {

    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
