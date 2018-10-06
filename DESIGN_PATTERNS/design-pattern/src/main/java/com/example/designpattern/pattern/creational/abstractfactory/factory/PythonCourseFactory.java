package com.example.designpattern.pattern.creational.abstractfactory.factory;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Code;
import com.example.designpattern.pattern.creational.abstractfactory.basicimpl.PythonArticle;
import com.example.designpattern.pattern.creational.abstractfactory.basicimpl.PythonVideo;
import com.example.designpattern.pattern.creational.abstractfactory.basic.Video;

/**
 * Created by geely
 */
public class PythonCourseFactory implements CourseFactory {

    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }

    @Override
    public Code getCode() {
        return null;
    }
}
