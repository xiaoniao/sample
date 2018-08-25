package com.example.elasticsearchexample.exception;

/**
 * Created by liuzhuang on 2018/7/27.
 */
public class CustomerEsException extends RuntimeException {

    public CustomerEsException() {
    }

    public CustomerEsException(String message) {
        super(message);
    }

    public CustomerEsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerEsException(Throwable cause) {
        super(cause);
    }

    public CustomerEsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
