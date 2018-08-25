package com.example.elasticsearchexample.document;

import com.example.elasticsearchexample.model.Post;

/**
 * Created by liuzhuang on 2018/7/27.
 */
public interface DocumentService {

    /**
     * 索引一篇文档
     *
     * @param post
     */
    void index(Post post);

    void get();

    void exists();

    void delete(String id);

    void update();

    void bulk();

    void multiGet();
}
