package com.example.designpattern.pattern.creational.abstractfactory.basicimpl;

import com.example.designpattern.pattern.creational.abstractfactory.basic.Code;

public class PythonCode extends Code {

    @Override
    public void submit() {
        System.out.println("提交Python代码");
    }
}
