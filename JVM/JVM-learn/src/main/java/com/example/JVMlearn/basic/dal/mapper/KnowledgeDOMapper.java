package com.example.JVMlearn.basic.dal.mapper;


import com.example.JVMlearn.basic.dal.dataobject.KnowledgeDO;

import java.util.List;

public interface KnowledgeDOMapper {

    Long insert(KnowledgeDO entity);

    Long update(KnowledgeDO entity);

    KnowledgeDO getByPrimary(Integer id);

    List<KnowledgeDO> listKnowledge();
}
