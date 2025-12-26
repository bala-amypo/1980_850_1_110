package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.service.ResourceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    
    private final ResourceRepository resourceRepository;
    
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
    
    @Override
    public Resource createResource(Resource resource) {
        if (resource.getResourceType() == null || resource.getResourceType().trim().isEmpty()) {
            throw new IllegalArgumentException("Resource type is required");
        }
        if (resource.getCapacity() == null || resource.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }
        if (resource.getResourceName() != null && resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new IllegalArgumentException("Resource with name already exists");
        }
        return resourceRepository.save(resource);
    }
    
    @Override
    public Resource getResource(Long id) {
        return resourceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }
    
    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}