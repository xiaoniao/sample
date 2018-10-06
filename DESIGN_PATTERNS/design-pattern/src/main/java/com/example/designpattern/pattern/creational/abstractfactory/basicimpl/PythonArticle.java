package com.example.designpattern.pattern.creational.abstractfactory.basicimpl;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;

/**
 * Created by geely
 */
public class PythonArticle extends Article {

    @Override
    public void getContent() {
        System.out.println("Python课程手记");
    }
}
