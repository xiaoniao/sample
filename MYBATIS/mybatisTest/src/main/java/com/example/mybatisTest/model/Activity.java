package com.example.mybatisTest.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

public class Activity {

    private Integer id;
    private String activityContent;
    private String activityImageUrl;
    private String linkUrl;
    private String managerUser;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer activityTypeId;

    /**
     * Mybatis 通过反射设值
     */

    public static void main(String[] args) {

        Class<?> cls = null;
        Object obj = null;
        try {
            cls = Class.forName("com.example.mybatisTest.model.Activity");
            obj = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setter(obj, "ActivityContent", "hello world!", String.class);
        setField(obj, cls, "managerUser","hello world!");

        Activity activity = (Activity) obj;
        System.out.println(activity.getManagerUser());
    }

    public static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setField(Object obj, Class<?> cls, String att, String value) {
        try {
            Field field = cls.getDeclaredField(att);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Activity() {

    }

    public Integer getId() {
        System.out.println("getId");
        return id;
    }

    public void setId(Integer id) {
        System.out.println("setId");
        this.id = id;
    }

    public String getActivityContent() {
        System.out.println("getActivityContent");
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        System.out.println("setActivityContent");
        this.activityContent = activityContent;
    }

    public String getActivityImageUrl() {
        System.out.println("getActivityImageUrl");
        return activityImageUrl;
    }

    public void setActivityImageUrl(String activityImageUrl) {
        System.out.println("setActivityImageUrl");
        this.activityImageUrl = activityImageUrl;
    }

    public String getLinkUrl() {
        System.out.println("getLinkUrl");
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        System.out.println("setLinkUrl");
        this.linkUrl = linkUrl;
    }

    public String getManagerUser() {
        System.out.println("getManagerUser");
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        System.out.println("setManagerUser");
        this.managerUser = managerUser;
    }

    public Integer getStatus() {
        System.out.println("getStatus");
        return status;
    }

    public void setStatus(Integer status) {
        System.out.println("setStatus");
        this.status = status;
    }

    public Date getCreateTime() {
        System.out.println("getCreateTime");
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        System.out.println("setCreateTime");
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        System.out.println("getUpdateTime");
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        System.out.println("setUpdateTime");
        this.updateTime = updateTime;
    }

    public Integer getActivityTypeId() {
        System.out.println("getActivityTypeId");
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        System.out.println("setActivityTypeId");
        this.activityTypeId = activityTypeId;
    }


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityContent='" + activityContent + '\'' +
                ", activityImageUrl='" + activityImageUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", managerUser='" + managerUser + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", activityTypeId=" + activityTypeId +
                '}';
    }
}
