package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_STAGE_KNOWLEDGE
 */
public class StudyStageKnowledgeDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * stageId 阶段ID.
     */
    private Long stageId;
    /**
     * knowledgeNo 知识点编号.
     */
    private Long knowledgeNo;
    /**
     * dateCreated 创建时间.
     */
    private Date dateCreated;
    /**
     * lastUpdated 最后一次修改时间.
     */
    private Date lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getKnowledgeNo() {
        return knowledgeNo;
    }

    public void setKnowledgeNo(Long knowledgeNo) {
        this.knowledgeNo = knowledgeNo;
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
