package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY
 */
public class StudyDO {

    /**
     * studyNo 学习任务编号.
     */
    private Long studyNo;
    /**
     * name 学习计划名称.
     */
    private String name;
    /**
     * status 状态,-1 删除, 0_下架, 1_已发布,2_已结束,3_已删除,4_待发布.
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


    public Long getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(Long studyNo) {
        this.studyNo = studyNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
