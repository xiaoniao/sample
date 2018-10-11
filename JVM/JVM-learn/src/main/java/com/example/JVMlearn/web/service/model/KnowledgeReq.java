package com.example.JVMlearn.web.service.model;


/**
 * Created by liuzhuang on 2018/7/28.
 */
public class KnowledgeReq {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 知识名称
     */
    private String knowledgeName;

    /**
     * 知识点图片地址
     */
    private String knowledgeImg;

    /**
     * 状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除
     */
    private String knowledgeStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgeImg() {
        return knowledgeImg;
    }

    public void setKnowledgeImg(String knowledgeImg) {
        this.knowledgeImg = knowledgeImg;
    }

    public String getKnowledgeStatus() {
        return knowledgeStatus;
    }

    public void setKnowledgeStatus(String knowledgeStatus) {
        this.knowledgeStatus = knowledgeStatus;
    }

    @Override
    public String toString() {
        return "KnowledgeReq{" +
                "id=" + id +
                ", knowledgeName='" + knowledgeName + '\'' +
                ", knowledgeImg='" + knowledgeImg + '\'' +
                ", knowledgeStatus='" + knowledgeStatus + '\'' +
                '}';
    }
}
