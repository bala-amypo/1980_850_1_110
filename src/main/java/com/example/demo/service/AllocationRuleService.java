package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class AllocationRuleService {

    private final AllocationRuleRepository allocationRuleRepository;

    public AllocationRuleService(AllocationRuleRepository allocationRuleRepository) {
        this.allocationRuleRepository = allocationRuleRepository;
    }

    // Get all allocation rules
    public List<AllocationRule> getAllRules() {
        return allocationRuleRepository.findAll();
    }

    // Get rule by ID
    public AllocationRule getRuleById(Long id) {
        return allocationRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Allocation Rule not found with id: " + id));
    }

    // Create new rule
    public AllocationRule createRule(AllocationRule rule) {
        return allocationRuleRepository.save(rule);
    }

    // Update existing rule
    public AllocationRule updateRule(Long id, AllocationRule updatedRule) {
        AllocationRule existingRule = getRuleById(id);
        existingRule.setRuleName(updatedRule.getRuleName());
        existingRule.setRuleType(updatedRule.getRuleType());
        existingRule.setPriorityWeight(updatedRule.getPriorityWeight());
        return allocationRuleRepository.save(existingRule);
    }

    // Delete rule
    public void deleteRule(Long id) {
        AllocationRule existingRule = getRuleById(id);
        allocationRuleRepository.delete(existingRule);
    }
}
