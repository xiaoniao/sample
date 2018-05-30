package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY
 */
public class StudyDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * studyNo 学习任务编号.
     */
    private String studyNo;
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
     * Set studyNo 学习任务编号.
     */
    public void setStudyNo(String studyNo){
        this.studyNo = studyNo;
    }

    /**
     * Get studyNo 学习任务编号.
     *
     * @return the string
     */
    public String getStudyNo(){
        return studyNo;
    }

    /**
     * Set name 学习计划名称.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get name 学习计划名称.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Set status 状态,-1 删除, 0_下架, 1_已发布,2_已结束,3_已删除,4_待发布.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Get status 状态,-1 删除, 0_下架, 1_已发布,2_已结束,3_已删除,4_待发布.
     *
     * @return the string
     */
    public String getStatus(){
        return status;
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
