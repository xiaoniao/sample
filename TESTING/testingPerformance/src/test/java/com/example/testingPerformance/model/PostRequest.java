package com.example.testingPerformance.model;

import okhttp3.MediaType;

/**
 * Created by liuzz on 2018/06/28
 */
public class PostRequest {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String url;

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
