package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_PROCESS
 */
public class StudyProcessDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * studyNo 学习计划编号.
     */
    private String studyNo;
    /**
     * studentNo 学员编号.
     */
    private String studentNo;
    /**
     * finishPercent 完成百分比%.
     */
    private Integer finishPercent;
    /**
     * status 学习状态,0_未开始,1_学习中,2_已完成,3_已过期.
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
     * Set studyNo 学习计划编号.
     */
    public void setStudyNo(String studyNo){
        this.studyNo = studyNo;
    }

    /**
     * Get studyNo 学习计划编号.
     *
     * @return the string
     */
    public String getStudyNo(){
        return studyNo;
    }

    /**
     * Set studentNo 学员编号.
     */
    public void setStudentNo(String studentNo){
        this.studentNo = studentNo;
    }

    /**
     * Get studentNo 学员编号.
     *
     * @return the string
     */
    public String getStudentNo(){
        return studentNo;
    }

    /**
     * Set finishPercent 完成百分比%.
     */
    public void setFinishPercent(Integer finishPercent){
        this.finishPercent = finishPercent;
    }

    /**
     * Get finishPercent 完成百分比%.
     *
     * @return the string
     */
    public Integer getFinishPercent(){
        return finishPercent;
    }

    /**
     * Set status 学习状态,0_未开始,1_学习中,2_已完成,3_已过期.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Get status 学习状态,0_未开始,1_学习中,2_已完成,3_已过期.
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
