package com.example.protobuf;

import com.example.protobuf.proto.First;
import com.example.protobuf.proto.Other;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzz on 2018/07/05
 */
public class ProtobufTest {

    private Logger log = LoggerFactory.getLogger(ProtobufTest.class);


    @Test
    public void testFirst() {
        First.SearchRequest searchRequest = First.SearchRequest.newBuilder().setPageNum(1).setQuery("request").setResultPerPage(1).build();
        log.info("{},{},{}", searchRequest.getPageNum(), searchRequest.getQuery(), searchRequest.getResultPerPage());

        searchRequest = First.SearchRequest.newBuilder().setPageNum(1).setResultPerPage(1).build();
        log.info("{},{},{}", searchRequest.getPageNum(), searchRequest.getQuery(), searchRequest.getResultPerPage());

        First.SearchRequest.Corpus corpus = searchRequest.getCorpus();
        log.info("{}", corpus);
        log.info("编译后框架增加的一个枚举值:{}", First.SearchRequest.Corpus.UNRECOGNIZED);


        First.Result result = First.Result.newBuilder().setUrl("http://").setTitle("title").addSnippets("a").build();
        First.SearchResult searchResult = First.SearchResult.newBuilder().addResults(result).build();
        List<First.Result> resultList = searchResult.getResultsList();
        for (First.Result entity : resultList) {
            log.info("{},{}", entity.getUrl(), entity.getTitle());
        }

        Other.Student student = searchResult.getStudent();
        log.info("{}", student);


        // 多层嵌套
        Other.Outer outer = Other.Outer.newBuilder().build();

        Other.Outer.MiddleAA middleAA = Other.Outer.MiddleAA.newBuilder().build();

        Other.Outer.MiddleAA.Inner inner = Other.Outer.MiddleAA.Inner.newBuilder().build();

        Other.Outer.MiddleBB middleBB = Other.Outer.MiddleBB.newBuilder().build();

        Other.Outer.MiddleBB.Inner innerb = Other.Outer.MiddleBB.Inner.newBuilder().build();


        // Any pack() unpack() ？
        ByteString byteString = ByteString.copyFrom("hello Any!", Charset.defaultCharset());

        com.google.protobuf.Any any = Any.newBuilder().setValue(byteString).build();

        First.ErrorStatus errorStatus = First.ErrorStatus.newBuilder().setDetail(any).build();

        com.google.protobuf.Any detail = errorStatus.getDetail();

        log.info("{}", detail);

        // one of
        First.SampleMessage sampleMessage = First.SampleMessage.newBuilder().putMap("age", "18").setName("name").setNickName("nickName").build();

        log.info("oneof : {}, {}", sampleMessage.getName() , sampleMessage.getNickName());

        // map
        Map<String, String> map = sampleMessage.getMapMap();
        log.info("map age:{}", map.get("age"));



        // service


    }
}