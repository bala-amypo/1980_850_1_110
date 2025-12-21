package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleService allocationRuleService;

    public AllocationRuleController(AllocationRuleService allocationRuleService) {
        this.allocationRuleService = allocationRuleService;
    }

    // ✅ Create a new rule
    @PostMapping
    public ResponseEntity<AllocationRule> createRule(@RequestBody AllocationRule rule) {
        AllocationRule created = allocationRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }

    // ✅ Get all rules
    @GetMapping
    public ResponseEntity<List<AllocationRule>> getAllRules() {
        return ResponseEntity.ok(allocationRuleService.getAllRules());
    }

    // ✅ Get rule by ID
    @GetMapping("/{id}")
    public ResponseEntity<AllocationRule> getRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(allocationRuleService.getRuleById(id));
    }

    // ✅ Delete rule by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRule(@PathVariable Long id) {
        allocationRuleService.deleteRule(id);
        return ResponseEntity.ok("Rule deleted successfully");
    }
}
