package com.example.RestTemplatesSample.integration.model;

import java.io.Serializable;

/**
 * Created by liuzz on 2018/05/04
 */

public class Result<D> implements Serializable {

    private static final int SUCCESS_CODE = 0;
    private D data;
    private boolean success;
    private int code;
    private String message;

    public Result() {
    }

    public static <D> Result<D> wrapSuccessfulResult(D data) {
        Result<D> result = new Result();
        result.data = data;
        result.success = true;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> wrapSuccessfulResult(String message, T data) {
        Result<T> result = new Result();
        result.data = data;
        result.success = true;
        result.code = 0;
        result.message = message;
        return result;
    }

    public static <D> Result<D> wrapErrorResult(int code, String message) {
        Result<D> result = new Result();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public D getData() {
        return this.data;
    }

    public Result<D> setData(D data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result<D> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return this.code;
    }

    public Result<D> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result<D> setMessage(String message) {
        this.message = message;
        return this;
    }


    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
