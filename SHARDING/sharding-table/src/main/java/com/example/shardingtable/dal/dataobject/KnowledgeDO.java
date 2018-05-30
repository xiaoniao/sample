package com.example.shardingtable.dal.dataobject;

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
    private String knowledgeNo;
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

    /**
     * Set id 主键.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id 主键.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set knowledgeNo 知识点编号.
     */
    public void setKnowledgeNo(String knowledgeNo){
        this.knowledgeNo = knowledgeNo;
    }

    /**
     * Get knowledgeNo 知识点编号.
     *
     * @return the string
     */
    public String getKnowledgeNo(){
        return knowledgeNo;
    }

    /**
     * Set knowledgeName 知识名称.
     */
    public void setKnowledgeName(String knowledgeName){
        this.knowledgeName = knowledgeName;
    }

    /**
     * Get knowledgeName 知识名称.
     *
     * @return the string
     */
    public String getKnowledgeName(){
        return knowledgeName;
    }

    /**
     * Set knowledgeImg 知识点图片地址.
     */
    public void setKnowledgeImg(String knowledgeImg){
        this.knowledgeImg = knowledgeImg;
    }

    /**
     * Get knowledgeImg 知识点图片地址.
     *
     * @return the string
     */
    public String getKnowledgeImg(){
        return knowledgeImg;
    }

    /**
     * Set knowledgeStatus 状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除.
     */
    public void setKnowledgeStatus(String knowledgeStatus){
        this.knowledgeStatus = knowledgeStatus;
    }

    /**
     * Get knowledgeStatus 状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除.
     *
     * @return the string
     */
    public String getKnowledgeStatus(){
        return knowledgeStatus;
    }

    /**
     * Set dateCreated 创建时间.
     */
    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }

    /**
     * Get dateCreated 创建时间.
     *
     * @return the string
     */
    public Date getDateCreated(){
        return dateCreated;
    }

    /**
     * Set lastUpdated 最后修改时间.
     */
    public void setLastUpdated(Date lastUpdated){
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get lastUpdated 最后修改时间.
     *
     * @return the string
     */
    public Date getLastUpdated(){
        return lastUpdated;
    }
}
