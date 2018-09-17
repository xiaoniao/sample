package com.example.JVMlearn.basic.service;


import com.example.JVMlearn.basic.dal.dataobject.KnowledgeDO;
import com.example.JVMlearn.basic.service.model.KnowledgeReq;

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
