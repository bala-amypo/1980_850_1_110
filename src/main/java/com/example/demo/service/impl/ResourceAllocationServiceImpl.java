package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {
    
    private final ResourceRequestRepository reqRepo;
    private final ResourceRepository resourceRepo;
    private final ResourceAllocationRepository allocRepo;
    
    public ResourceAllocationServiceImpl(ResourceRequestRepository reqRepo, 
                                       ResourceRepository resourceRepo, 
                                       ResourceAllocationRepository allocRepo) {
        this.reqRepo = reqRepo;
        this.resourceRepo = resourceRepo;
        this.allocRepo = allocRepo;
    }
    
    @Override
    public ResourceAllocation autoAllocate(Long requestId) {
        ResourceRequest request = reqRepo.findById(requestId)
            .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        
        List<Resource> candidates = resourceRepo.findByResourceType(request.getResourceType());
        if (candidates.isEmpty()) {
            throw new IllegalArgumentException("No resources available for allocation");
        }
        
        Resource selectedResource = candidates.get(0); // Simple first-available strategy
        
        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setResource(selectedResource);
        allocation.setRequest(request);
        allocation.setConflictFlag(false);
        
        return allocRepo.save(allocation);
    }
    
    @Override
    public ResourceAllocation getAllocation(Long id) {
        return allocRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Allocation not found"));
    }
    
    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocRepo.findAll();
    }
}