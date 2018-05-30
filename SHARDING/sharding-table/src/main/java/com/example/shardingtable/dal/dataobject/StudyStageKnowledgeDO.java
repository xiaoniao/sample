package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_STAGE_KNOWLEDGE
 */
public class StudyStageKnowledgeDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * stageId 阶段ID.
     */
    private Integer stageId;
    /**
     * knowledgeNo 知识点编号.
     */
    private String knowledgeNo;
    /**
     * dateCreated 创建时间.
     */
    private Date dateCreated;
    /**
     * lastUpdated 最后一次修改时间.
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
     * Set stageId 阶段ID.
     */
    public void setStageId(Integer stageId){
        this.stageId = stageId;
    }

    /**
     * Get stageId 阶段ID.
     *
     * @return the string
     */
    public Integer getStageId(){
        return stageId;
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
     * Set lastUpdated 最后一次修改时间.
     */
    public void setLastUpdated(Date lastUpdated){
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get lastUpdated 最后一次修改时间.
     *
     * @return the string
     */
    public Date getLastUpdated(){
        return lastUpdated;
    }
}
