package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDY_KNOWLEDGE_PROCESS
 */
public class StudyKnowledgeProcessDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * studyNo 学习编号.
     */
    private String studyNo;
    /**
     * studentNo 学员编号.
     */
    private String studentNo;
    /**
     * knowledgeNo 知识点编号.
     */
    private String knowledgeNo;
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
     * Set knowledgeNo 知识点编号.
     */
    public void setKnowledgeNo(String knowledgeNo){
        this.knowledgeNo = knowledgeNo;
    }

    /**
     * Get knowledgeNo 知识点编号.
     *
     * @return the string
     */
    public String getKnowledgeNo(){
        return knowledgeNo;
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
     * Set status 知识点状态,0_未开始,1_学习中,2_已完成未领取,3_已领取.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Get status 知识点状态,0_未开始,1_学习中,2_已完成未领取,3_已领取.
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
