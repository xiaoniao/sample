package com.example.mineStarter;

/**
 * Created by liuzz on 2018/04/02
 */
public class ExampleService {

    private String prefix;

    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String wrod) {
        return prefix + wrod + suffix;
    }
}
