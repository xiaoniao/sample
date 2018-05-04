package com.example.javaLearn.service;

import com.example.javaLearn.exception.BizException;
import org.springframework.stereotype.Component;

/**
 * 继承 RuntimeException 的异常 可以不在方法上声明和必须捕获
 *
 * Exception 和 RuntimeException 区别
 *
 *
 * RuntimeException : 是一种在运行时抛出的异常，它和它的子类属于未检查异常，不需要在方法上声明异常和捕获异常。
 *  RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the Java Virtual Machine.
 *  RuntimeException and its subclasses are unchecked exceptions. Unchecked exceptions do not need to be declared in a method or constructor's throws clause
 *  if they can be thrown by the execution of the method or constructor and propagate outside the method or constructor boundary.
 *
 *
 * Exception : 检查型异常，必须在方法放指定异常或者捕获异常。
 *
 *
 * 在java的异常类体系中,Error和RuntimeException是非检查型异常，其他的都是检查型异常。
 *
 * 补充资料：https://blog.csdn.net/liuj2511981/article/details/8524418 这篇文章讲的清楚
 *
 */
@Component
public class TestService {

    public void testWithException() throws Exception {
        throw new Exception();
    }

    public void testWithRuntimeException() {
        throw new RuntimeException();
    }

    public void testWithBizException() {
        throw new BizException();
    }
}
