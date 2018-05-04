package com.example.RestTemplatesSample.integration.model;

/**
 * Created by liuzz on 2018/05/04
 */
public class HttpResult<D> {

    private Integer result;

    private D content;

    private String errMsg;


    public static <D> HttpResult<D> wrapSuccessfulResult(D content) {
        HttpResult<D> result = new HttpResult<>();
        result.content = content;
        result.result = 200;
        return result;
    }

    public static <D> HttpResult<D> wrapErrorResult(String errMsg) {
        HttpResult<D> result = new HttpResult<>();
        result.errMsg = null;
        result.result = 4000;
        return result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public D getContent() {
        return content;
    }

    public void setContent(D content) {
        this.content = content;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
