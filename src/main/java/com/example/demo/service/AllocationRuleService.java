package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AllocationRule;

public interface AllocationRuleService {

	public List<AllocationRule> getAllRules();

	public AllocationRule getRuleById(Long id);

	public AllocationRule createRule(AllocationRule rule);

	public AllocationRule updateRule(Long id, AllocationRule updatedRule);

	public void deleteRule(Long id);

}
