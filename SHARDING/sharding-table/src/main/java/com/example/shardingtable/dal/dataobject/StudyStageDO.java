package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_STAGE
 */
public class StudyStageDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * studyNo 学习编号.
     */
    private Long studyNo;
    /**
     * stageId 阶段ID.
     */
    private Long stageId;
    /**
     * sortNum 阶段序号.
     */
    private Integer sortNum;
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

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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
