package com.nistagram.agent.repository;

import com.nistagram.agent.model.entity.Campaign;
import com.nistagram.agent.model.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("select c from Campaign c join c.criteria crit where (crit.gender = ?1 or crit.gender = ?2) and crit.minAge >= ?3 and crit.maxAge <= ?3")
    List<Campaign> findAllCriteria(String gender, String allGender, int age);
    List<Campaign> findByAgentUsername(String agentUsername);
}
