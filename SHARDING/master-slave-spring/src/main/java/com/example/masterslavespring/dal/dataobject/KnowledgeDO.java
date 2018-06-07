package com.example.masterslavespring.dal.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "tp_knowledge")
public class KnowledgeDO {

    @Id
    private Long knowledgeNo;

    private String knowledgeName;

    private String knowledgeImg;

    private String knowledgeStatus;

    private Date dateCreated;

    private Date lastUpdated;

    public Long getKnowledgeNo() {
        return knowledgeNo;
    }

    public void setKnowledgeNo(Long knowledgeNo) {
        this.knowledgeNo = knowledgeNo;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgeImg() {
        return knowledgeImg;
    }

    public void setKnowledgeImg(String knowledgeImg) {
        this.knowledgeImg = knowledgeImg;
    }

    public String getKnowledgeStatus() {
        return knowledgeStatus;
    }

    public void setKnowledgeStatus(String knowledgeStatus) {
        this.knowledgeStatus = knowledgeStatus;
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
