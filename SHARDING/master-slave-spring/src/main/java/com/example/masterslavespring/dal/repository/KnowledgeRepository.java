package com.example.masterslavespring.dal.repository;

import com.example.masterslavespring.dal.dataobject.KnowledgeDO;
import com.example.masterslavespring.masterslave.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liuzz on 2018/06/07
 * QueryByExampleExecutor<KnowledgeDO>
 */
public interface KnowledgeRepository extends JpaRepository<KnowledgeDO, Long> {

    List<KnowledgeDO> findByKnowledgeName(String knowledgeName);
}
