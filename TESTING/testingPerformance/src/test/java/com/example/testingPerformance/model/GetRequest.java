package com.example.testingPerformance.model;

/**
 * Created by liuzz on 2018/06/28
 */
public class GetRequest {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static GetRequest create(String url) {
        GetRequest getRequest = new GetRequest();
        getRequest.setUrl(url);
        return getRequest;
    }
}
