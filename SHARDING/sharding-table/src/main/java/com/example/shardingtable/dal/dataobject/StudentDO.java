package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDENT
 */
public class StudentDO{

    /**
     * studentNo 学员编号.
     */
    private Long studentNo;
    /**
     * name 学员名称.
     */
    private String name;
    /**
     * status 是否可用，0_已禁用,1_已启用.
     */
    private Integer status;
    /**
     * dateCreated 创建日期.
     */
    private Date dateCreated;
    /**
     * lastUpdated 最近更新时间.
     */
    private Date lastUpdated;


    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
