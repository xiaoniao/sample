package com.example.JVMlearn.web.service;


import com.example.JVMlearn.web.dal.dataobject.KnowledgeDO;
import com.example.JVMlearn.web.service.model.KnowledgeReq;

import java.util.List;

/**
 * Created by liuzhuang on 2018/7/28.
 */
public interface KnowledgeService {

    void addKnowledge(KnowledgeReq knowledgeReq);

    void updateKnowledge(KnowledgeReq knowledgeReq);

    void deleteKnowledge(Integer id);

    List<KnowledgeDO> listKnowledge();
}
