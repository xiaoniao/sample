package com.example.shardingtable.dal.dataobject;

import java.util.Date;

/**
 * The table TP_STUDENT
 */
public class StudentDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * studentNo 学员编号.
     */
    private String studentNo;
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
     * Set name 学员名称.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get name 学员名称.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Set status 是否可用，0_已禁用,1_已启用.
     */
    public void setStatus(Integer status){
        this.status = status;
    }

    /**
     * Get status 是否可用，0_已禁用,1_已启用.
     *
     * @return the string
     */
    public Integer getStatus(){
        return status;
    }

    /**
     * Set dateCreated 创建日期.
     */
    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }

    /**
     * Get dateCreated 创建日期.
     *
     * @return the string
     */
    public Date getDateCreated(){
        return dateCreated;
    }

    /**
     * Set lastUpdated 最近更新时间.
     */
    public void setLastUpdated(Date lastUpdated){
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get lastUpdated 最近更新时间.
     *
     * @return the string
     */
    public Date getLastUpdated(){
        return lastUpdated;
    }
}
