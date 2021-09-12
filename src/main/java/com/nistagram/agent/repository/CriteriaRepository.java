package com.nistagram.agent.repository;

import com.nistagram.agent.model.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
