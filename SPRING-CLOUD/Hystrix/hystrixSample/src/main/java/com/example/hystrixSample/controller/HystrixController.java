package com.example.hystrixSample.controller;

import com.example.hystrixSample.command.CommandHelloWorld;
import com.example.hystrixSample.command.CommandHttpCall;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

/**
 * Created by liuzz on 2018/05/03
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    private Logger log = LoggerFactory.getLogger(HystrixController.class);

    /**
     * future模式 get时执行
     */
    @RequestMapping("/future")
    public String getFuture() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        Future<String> productSyncCall = new CommandHttpCall("http://localhost:8080/product").queue();
        try {
            String product = productSyncCall.get();
            log.info("future get product {}", product);
            result.append(product);

            Future<String> cartSyncCall = new CommandHttpCall("http://localhost:8080/cart").queue();
            Future<String> orderSyncCall = new CommandHttpCall("http://localhost:8080/order").queue();
            String cart = cartSyncCall.get();
            String order = orderSyncCall.get();
            log.info("future get cart {}", cart);
            log.info("future get order {}", order);
            result.append(cart);
            result.append(order);

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new CommandHelloWorld(result.toString()).execute();
    }

    /**
     * 异步直接返回
     */
    @RequestMapping("/observe")
    public String getObserve() throws InterruptedException {
        Observable<String> productCall = new CommandHttpCall("http://localhost:8080/product").observe();
        Observable<String> orderCall = new CommandHttpCall("http://localhost:8080/order").observe();
        Observable<String> cartCall = new CommandHttpCall("http://localhost:8080/cart").observe();

        List<Observable<String>> result = new ArrayList<>();
        result.add(productCall);
        result.add(orderCall);
        Observable.merge(result).subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                System.out.println("product&order call complete");
                cartCall.subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("cart call complete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String v) {
                        System.out.println("onNext: " + v);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                log.info("异常: ", e);
            }

            @Override
            public void onNext(String v) {
                System.out.println("product&order onNext: " + v);
            }

        });
        return new CommandHelloWorld("this is content for observe").execute();
    }
}
