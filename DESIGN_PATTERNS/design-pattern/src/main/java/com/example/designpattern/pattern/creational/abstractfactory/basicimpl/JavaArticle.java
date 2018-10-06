package com.example.designpattern.pattern.creational.abstractfactory.basicimpl;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Article;

/**
 * Created by geely
 */
public class JavaArticle extends Article {

    @Override
    public void getContent() {
        System.out.println("这是手记");
    }
}
