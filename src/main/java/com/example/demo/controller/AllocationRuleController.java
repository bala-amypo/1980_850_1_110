package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {
    
    private final AllocationRuleService ruleService;
    
    public AllocationRuleController(AllocationRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    public ResponseEntity<AllocationRule> createRule(@RequestBody AllocationRule rule) {
        AllocationRule savedRule = ruleService.createRule(rule);
        return ResponseEntity.ok(savedRule);
    }
    
    @GetMapping
    public ResponseEntity<List<AllocationRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AllocationRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRule(id));
    }
}