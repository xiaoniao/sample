package com.example.springmvclearn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by liuzz on 2018/06/29
 */
@RestController
@RequestMapping("/")
public class HelloController {

    /**
     * Thread.sleep(500000);
     *
     * [nio-8080-exec-8] o.apache.catalina.core.AsyncContextImpl : onTimeout() failed for listener of type [org.apache.catalina.core.AsyncListenerWrapper]
     *
     * java.lang.IllegalArgumentException: Cannot dispatch without an AsyncContext
     *
     *
     * http 达到了 websocket的效果， 是放到队列中，自行完毕后再给http响应数据吗？
     */
    @RequestMapping(value = "/call")
    public Callable<String> callable() {
        System.out.println(Thread.currentThread().getName());

        Callable<String> result = () -> {
            Thread.sleep(5000);
            System.out.println("close socket");
            return "return hello";
        };
        System.out.println("return");
        return result;
    }

    @RequestMapping(value = "/deferredResult")
    public DeferredResult<String> deferredResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("close socket");
            deferredResult.setResult("return hello");
        }).start();

        System.out.println("return");
        return deferredResult;
    }
}
