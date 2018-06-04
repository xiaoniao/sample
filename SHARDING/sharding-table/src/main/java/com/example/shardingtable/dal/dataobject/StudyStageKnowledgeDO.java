package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_STAGE_KNOWLEDGE
 */
public class StudyStageKnowledgeDO {

    /**
     * id 主键.
     */
    private Long id;
    /**
     * 学习ID.
     */
    private Long studyNo;
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

    private String stageName;

    private Integer sortNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(Long studyNo) {
        this.studyNo = studyNo;
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

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}
