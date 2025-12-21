package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    // Get all resources
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // Get resource by ID
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }

    // Create new resource
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    // Update existing resource
    public Resource updateResource(Long id, Resource updatedResource) {
        Resource existingResource = getResourceById(id);
        existingResource.setResourceName(updatedResource.getResourceName());
        existingResource.setResourceType(updatedResource.getResourceType());
        existingResource.setAvailable(updatedResource.isAvailable());
        existingResource.setDescription(updatedResource.getDescription());
        return resourceRepository.save(existingResource);
    }

    // Delete resource
    public void deleteResource(Long id) {
        Resource resource = getResourceById(id);
        resourceRepository.delete(resource);
    }

    // Get available resources
    public List<Resource> getAvailableResources() {
        return resourceRepository.findByAvailableTrue();
    }

    // Get resources by type
    public List<Resource> getResourcesByType(String type) {
        return resourceRepository.findByResourceType(type);
    }
}
