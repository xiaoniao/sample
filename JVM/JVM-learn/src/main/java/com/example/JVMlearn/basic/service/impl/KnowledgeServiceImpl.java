package com.example.JVMlearn.basic.service.impl;

import com.example.JVMlearn.basic.dal.dataobject.KnowledgeDO;
import com.example.JVMlearn.basic.dal.mapper.KnowledgeDOMapper;
import com.example.JVMlearn.basic.service.KnowledgeService;
import com.example.JVMlearn.basic.service.exception.BizException;
import com.example.JVMlearn.basic.service.model.KnowledgeReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuzhuang on 2018/7/28.
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDOMapper knowledgeDOMapper;

    @Override
    public void addKnowledge(KnowledgeReq knowledgeReq) {
        KnowledgeDO knowledgeDO = new KnowledgeDO();
        BeanUtils.copyProperties(knowledgeReq, knowledgeDO);
        Long result = knowledgeDOMapper.insert(knowledgeDO);
        if (result == null || result != 1) {
            throw new BizException("db error");
        }
    }

    @Override
    public void updateKnowledge(KnowledgeReq knowledgeReq) {
        checkKnowledgeIsExist(knowledgeReq.getId());
        KnowledgeDO knowledgeDO = new KnowledgeDO();
        BeanUtils.copyProperties(knowledgeReq, knowledgeDO);
        Long result = knowledgeDOMapper.update(knowledgeDO);
        if (result == null || result != 1) {
            throw new BizException("db error");
        }
    }

    @Override
    public void deleteKnowledge(Integer id) {
        KnowledgeDO knowledgeDO = checkKnowledgeIsExist(id);
        knowledgeDO.setKnowledgeStatus("5");
        Long result = knowledgeDOMapper.update(knowledgeDO);
        if (result == null || result != 1) {
            throw new BizException("db error");
        }
    }

    @Override
    public List<KnowledgeDO> listKnowledge() {
        return knowledgeDOMapper.listKnowledge();
    }

    private KnowledgeDO checkKnowledgeIsExist(Integer id) {
        KnowledgeDO knowledgeDO = knowledgeDOMapper.getByPrimary(id);
        if (knowledgeDO == null) {
            throw new BizException("data not exist");
        }
        return knowledgeDO;
    }
}
