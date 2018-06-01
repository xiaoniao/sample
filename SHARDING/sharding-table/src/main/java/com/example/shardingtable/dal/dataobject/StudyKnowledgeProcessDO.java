package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_KNOWLEDGE_PROCESS
 */
public class StudyKnowledgeProcessDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * studyNo 学习编号.
     */
    private Long studyNo;
    /**
     * studentNo 学员编号.
     */
    private Long studentNo;
    /**
     * knowledgeNo 知识点编号.
     */
    private Long knowledgeNo;
    /**
     * finishPercent 完成百分比%.
     */
    private Integer finishPercent;
    /**
     * status 知识点状态,0_未开始,1_学习中,2_已完成未领取,3_已领取.
     */
    private String status;
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

    public Long getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(Long studyNo) {
        this.studyNo = studyNo;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public Long getKnowledgeNo() {
        return knowledgeNo;
    }

    public void setKnowledgeNo(Long knowledgeNo) {
        this.knowledgeNo = knowledgeNo;
    }

    public Integer getFinishPercent() {
        return finishPercent;
    }

    public void setFinishPercent(Integer finishPercent) {
        this.finishPercent = finishPercent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
