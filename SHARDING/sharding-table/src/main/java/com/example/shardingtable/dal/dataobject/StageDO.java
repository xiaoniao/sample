package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STAGE
 */
public class StageDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * stageName 阶段名称.
     */
    private String stageName;
    /**
     * stageDesc 阶段简介.
     */
    private String stageDesc;
    /**
     * dateCreated DATE_CREATED.
     */
    private Date dateCreated;
    /**
     * lastUpdated LAST_UPDATED.
     */
    private Date lastUpdated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageDesc() {
        return stageDesc;
    }

    public void setStageDesc(String stageDesc) {
        this.stageDesc = stageDesc;
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
