package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_STAGE
 */
public class StudyStageDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * studyNo 学习编号.
     */
    private String studyNo;
    /**
     * stageId 阶段ID.
     */
    private Integer stageId;
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
     * Set studyNo 学习编号.
     */
    public void setStudyNo(String studyNo){
        this.studyNo = studyNo;
    }

    /**
     * Get studyNo 学习编号.
     *
     * @return the string
     */
    public String getStudyNo(){
        return studyNo;
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
     * Set sortNum 阶段序号.
     */
    public void setSortNum(Integer sortNum){
        this.sortNum = sortNum;
    }

    /**
     * Get sortNum 阶段序号.
     *
     * @return the string
     */
    public Integer getSortNum(){
        return sortNum;
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
