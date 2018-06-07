package com.example.masterslavespring.dal.repository;

import com.example.masterslavespring.dal.dataobject.KnowledgeDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liuzz on 2018/06/07
 * QueryByExampleExecutor<KnowledgeDO>
 */
public interface KnowledgeRepository extends JpaRepository<KnowledgeDO, Long> {
}
