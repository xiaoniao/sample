package com.example.masterslave.dal.dataobject;

import java.util.Date;

/**
 * The table TP_KNOWLEDGE
 */
public class KnowledgeDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * knowledgeNo 知识点编号.
     */
    private Long knowledgeNo;
    /**
     * knowledgeName 知识名称.
     */
    private String knowledgeName;
    /**
     * knowledgeImg 知识点图片地址.
     */
    private String knowledgeImg;
    /**
     * knowledgeStatus 状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除.
     */
    private String knowledgeStatus;
    /**
     * dateCreated 创建时间.
     */
    private Date dateCreated;
    /**
     * lastUpdated 最后修改时间.
     */
    private Date lastUpdated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getKnowledgeNo() {
        return knowledgeNo;
    }

    public void setKnowledgeNo(Long knowledgeNo) {
        this.knowledgeNo = knowledgeNo;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
