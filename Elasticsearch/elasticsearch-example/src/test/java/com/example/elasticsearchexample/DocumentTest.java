package com.example.elasticsearchexample;

import com.example.elasticsearchexample.document.DocumentService;
import com.example.elasticsearchexample.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzhuang on 2018/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentTest {

    @Autowired
    private DocumentService documentService;

    @Test
    public void testIndex() {
        Post post = new Post();
        post.setUser("jack");
        post.setPostDate("2018-07-27");
        post.setMessage("昨天美使馆附近发生爆炸1");
        documentService.index(post);
        Utils.sleep();
    }

    @Test
    public void testDelete() {
        documentService.delete("fMli2WQB5Q_pbLfo8Rqv");
        Utils.sleep();
    }
}
