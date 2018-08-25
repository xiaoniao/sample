package com.example.elasticsearchexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by liuzhuang on 2018/7/27.
 */
public class Post {

    private String id;

    @JsonIgnore
    private String documentId;

    private String user;

    private String postDate;

    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
