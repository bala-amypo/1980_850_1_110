package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "allocation_rules")
public class AllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;   // e.g. "Default Rule"

    @Column(nullable = false)
    private String ruleType;   // FIRSTAVAILABLE, PRIORITYBASED, ROUNDROBIN

    private Integer priorityWeight; // used for priority-based rules

    private LocalDateTime createdAt = LocalDateTime.now();

    public AllocationRule() {}

    public AllocationRule(String ruleName, String ruleType, Integer priorityWeight) {
        this.ruleName = ruleName;
        this.ruleType = ruleType;
        this.priorityWeight = priorityWeight;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }

    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getRuleType() { return ruleType; }

    public void setRuleType(String ruleType) { this.ruleType = ruleType; }

    public Integer getPriorityWeight() { return priorityWeight; }

    public void setPriorityWeight(Integer priorityWeight) { this.priorityWeight = priorityWeight; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
