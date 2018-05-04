package com.example.RestTemplatesSample.integration.exception;

/**
 *
 * 封装HTTP请求错误信息 包括HTTP错误和业务异常
 *
 * Created by liuzz on 2018/05/04
 */
public class RestException extends RuntimeException {


    public RestException() {

    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
