package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {
    
    private final ResourceRequestService requestService;
    
    public ResourceRequestController(ResourceRequestService requestService) {
        this.requestService = requestService;
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<ResourceRequest> createRequest(@PathVariable Long userId, @RequestBody ResourceRequest request) {
        ResourceRequest savedRequest = requestService.createRequest(userId, request);
        return ResponseEntity.ok(savedRequest);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResourceRequest>> getRequestsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByUser(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResourceRequest> getRequest(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequest(id));
    }
    
    @PutMapping("/status/{requestId}")
    public ResponseEntity<ResourceRequest> updateRequestStatus(@PathVariable Long requestId, @RequestParam String status) {
        ResourceRequest updatedRequest = requestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(updatedRequest);
    }
}