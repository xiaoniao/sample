package com.example.designpattern.pattern.creational.abstractfactory;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Code;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;
import com.example.designpattern.pattern.creational.abstractfactory.factory.CourseFactory;
import com.example.designpattern.pattern.creational.abstractfactory.factory.JavaCourseFactory;

/**
 * Created by geely
 *
 * 抽象工厂模式
 *    1.抽象factory
 *    2.抽象业务
 *    factory实例化对象
 */
public class Test {

    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        Code code = courseFactory.getCode();
        video.startPlay();
        article.getContent();
        code.submit();
    }
}
