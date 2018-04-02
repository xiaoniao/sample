package com.example.mybatisTest.model;

import java.util.Date;

public class Notice {

    private Integer id;
    private String noticeTop;
    private String noticeContent;
    private Boolean noticeState;
    private Date beginShowTime;
    private Date endShowTime;
    private String managerUser;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeTop() {
        return noticeTop;
    }

    public void setNoticeTop(String noticeTop) {
        this.noticeTop = noticeTop;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Boolean getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(Boolean noticeState) {
        this.noticeState = noticeState;
    }

    public Date getBeginShowTime() {
        return beginShowTime;
    }

    public void setBeginShowTime(Date beginShowTime) {
        this.beginShowTime = beginShowTime;
    }

    public Date getEndShowTime() {
        return endShowTime;
    }

    public void setEndShowTime(Date endShowTime) {
        this.endShowTime = endShowTime;
    }

    public String getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", noticeTop='" + noticeTop + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeState=" + noticeState +
                ", beginShowTime=" + beginShowTime +
                ", endShowTime=" + endShowTime +
                ", managerUser='" + managerUser + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
