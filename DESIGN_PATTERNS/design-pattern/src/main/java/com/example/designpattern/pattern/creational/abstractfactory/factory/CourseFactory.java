package com.example.designpattern.pattern.creational.abstractfactory.factory;


import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;

/**
 * Created by geely
 */
public interface CourseFactory {

    Video getVideo();

    Article getArticle();
}
