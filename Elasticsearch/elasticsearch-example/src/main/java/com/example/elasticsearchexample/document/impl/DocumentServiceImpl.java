package com.example.elasticsearchexample.document.impl;

import com.example.elasticsearchexample.document.DocumentService;
import com.example.elasticsearchexample.exception.CustomerEsException;
import com.example.elasticsearchexample.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文档操作API
 *
 * Created by liuzhuang on 2018/7/26.
 */
@Component
public class DocumentServiceImpl implements DocumentService {

    private Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestHighLevelClient client;

    /**
     * 可选参数:
     * request.routing("routing");
     * request.parent("parent");
     * request.timeout(TimeValue.timeValueSeconds(1));
     * request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
     * request.version(2);
     * request.versionType(VersionType.EXTERNAL);
     * request.opType(DocWriteRequest.OpType.CREATE);
     * request.setPipeline("pipeline");
     */
    private IndexRequest createIndexRequest(Post post) {
        IndexRequest request = new IndexRequest("posts", "doc");
        try {
            request.source(objectMapper.writeValueAsBytes(post), XContentType.JSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new CustomerEsException(e);
        }
        return request;
    }

    private void parseResponse(IndexResponse indexResponse) {
        log.info("indexResponse:{}", indexResponse.toString());

        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("新增");
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("更新");
        }

        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            log.info("有分片创建/更新失败 total:{}, success:{}", shardInfo.getTotal(), shardInfo.getSuccessful());
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                log.info("exception:{}", failure.reason());
            }
        }
    }

    @Override
    public void index(Post post) {
        IndexRequest indexRequest = createIndexRequest(post);
        client.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                post.setMessage("");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                if (e instanceof ElasticsearchException) {
                    log.info("status:{}", ((ElasticsearchException) e).status());
                }
            }
        });
    }

    @Override
    public void get() {

    }

    @Override
    public void exists() {

    }

    private DeleteRequest createDeleteRequest(String id) {
        DeleteRequest request = new DeleteRequest("posts", "doc", id);
        return request;
    }

    @Override
    public void delete(String id) {
        DeleteRequest request = createDeleteRequest(id);
        client.deleteAsync(request, new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                    log.info("文档不存在");
                } else if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED){
                    log.info("删除成功");
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                if (e instanceof ElasticsearchException) {
                    log.info("status:{}", ((ElasticsearchException) e).status());
                }
            }
        });
    }

    @Override
    public void update() {

    }

    @Override
    public void bulk() {

    }

    @Override
    public void multiGet() {

    }
}
