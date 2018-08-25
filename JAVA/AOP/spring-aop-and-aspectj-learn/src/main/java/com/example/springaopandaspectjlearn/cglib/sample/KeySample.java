package com.example.springaopandaspectjlearn.cglib.sample;

import net.sf.cglib.core.KeyFactory;

/**
 * Created by liuzz on 2018/06/11
 */
public class KeySample {

    private interface MyFactory {
        Object newInstance(int a, char[] b, String d);
    }

    public static void main(String[] args) {
        MyFactory myFactory = (MyFactory) KeyFactory.create(MyFactory.class);

        Object key1 = myFactory.newInstance(20, new char[]{'a', 'b'}, "hello");
        Object key2 = myFactory.newInstance(20, new char[]{'a', 'b'}, "hello");
        Object key3 = myFactory.newInstance(20, new char[]{'a', '_'}, "hello");

        System.out.println(key1.equals(key2));
        System.out.println(key2.equals(key3));
    }
}