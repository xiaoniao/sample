package com.example.springaopandaspectjlearn.cglib.sample1;

/**
 *
 * https://blog.csdn.net/zghwaicsdn/article/details/50957474
 *
 * Created by liuzz on 2018/06/11
 */
public class TargetObject {

    public String method1(String paramName) {
        return paramName;
    }

    public int method2(int count) {
        return count;
    }

    public int method3(int count) {
        return count;
    }

    @Override
    public String toString() {
        return "TargetObject []" + getClass();
    }
}
