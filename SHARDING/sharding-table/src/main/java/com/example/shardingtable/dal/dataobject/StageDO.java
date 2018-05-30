package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STAGE
 */
public class StageDO{

    /**
     * id ID.
     */
    private Integer id;
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

    /**
     * Set id ID.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set stageName 阶段名称.
     */
    public void setStageName(String stageName){
        this.stageName = stageName;
    }

    /**
     * Get stageName 阶段名称.
     *
     * @return the string
     */
    public String getStageName(){
        return stageName;
    }

    /**
     * Set stageDesc 阶段简介.
     */
    public void setStageDesc(String stageDesc){
        this.stageDesc = stageDesc;
    }

    /**
     * Get stageDesc 阶段简介.
     *
     * @return the string
     */
    public String getStageDesc(){
        return stageDesc;
    }

    /**
     * Set dateCreated DATE_CREATED.
     */
    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }

    /**
     * Get dateCreated DATE_CREATED.
     *
     * @return the string
     */
    public Date getDateCreated(){
        return dateCreated;
    }

    /**
     * Set lastUpdated LAST_UPDATED.
     */
    public void setLastUpdated(Date lastUpdated){
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get lastUpdated LAST_UPDATED.
     *
     * @return the string
     */
    public Date getLastUpdated(){
        return lastUpdated;
    }
}
