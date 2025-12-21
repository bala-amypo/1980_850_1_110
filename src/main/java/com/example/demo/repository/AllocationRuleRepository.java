package com.example.demo.repository;

import com.example.demo.entity.AllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllocationRuleRepository extends JpaRepository<AllocationRule, Long> {

    // Find rule by name
    Optional<AllocationRule> findByRuleName(String ruleName);

    // Find rule by type
    Optional<AllocationRule> findByRuleType(String ruleType);
}
