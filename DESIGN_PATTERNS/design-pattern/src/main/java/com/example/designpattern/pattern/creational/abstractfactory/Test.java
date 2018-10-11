package com.example.designpattern.pattern.creational.abstractfactory;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Code;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;
import com.example.designpattern.pattern.creational.abstractfactory.factory.CourseFactory;
import com.example.designpattern.pattern.creational.abstractfactory.factory.JavaCourseFactory;

/**
 * Created by geely
 *
 * 产品族（多个产品），一个工厂类生产多个产品，例如Video，Article，Code
 *
 * 抽象出一个工厂类CourseFactory，多个产品类Video，Article，Code
 *
 * 适合于抽象出来的产品族是固定的，而具体的产品是不固定的。
 *
 *  例如增加：Python产品
 *      PythonArticle
 *      PythonCode
 *      PythonVideo
 *      PythonCourseFactory
 *
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
