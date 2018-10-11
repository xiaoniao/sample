package com.example.JVMlearn.web.controller.oom;

import com.example.JVMlearn.bytecode.TestClass;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhuang on 2018/8/13.
 */
@RestController()
@RequestMapping(method = RequestMethod.GET, value = "/")
public class OOMController {

    private List<TestClass> testClassList = new ArrayList<>();

    private List<Class<?>> classList = new ArrayList<>();

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }

    /**
     * -Xmx32M -Xms32M
     *
     * Error: java.lang.OutOfMemoryError: GC overhead limit exceeded
     */
    @RequestMapping(value = "/heap")
    public String heap() {
        while (true) {
            testClassList.add(new TestClass());
        }
    }

    /**
     * -XX:MetaspaceSize=100M -XX:MaxMetaspaceSize=100M
     *
     * Error: java.lang.OutOfMemoryError: Metaspace
     */
    @RequestMapping(value = "/nonheap")
    public String nonheap() {
        while (true) {
            classList.addAll(Metaspace.createClass());
        }
    }
}
