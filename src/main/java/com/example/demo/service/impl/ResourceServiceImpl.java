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
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(Long id, Resource updatedResource) {
        Resource existingResource = getResourceById(id);
        existingResource.setResourceName(updatedResource.getResourceName());
        existingResource.setResourceType(updatedResource.getResourceType());
        existingResource.setAvailable(updatedResource.isAvailable());
        existingResource.setDescription(updatedResource.getDescription());
        return resourceRepository.save(existingResource);
    }

    @Override
    public void deleteResource(Long id) {
        Resource resource = getResourceById(id);
        resourceRepository.delete(resource);
    }

    @Override
    public List<Resource> getAvailableResources() {
        return resourceRepository.findByAvailableTrue();
    }

    @Override
    public List<Resource> getResourcesByType(String type) {
        return resourceRepository.findByResourceType(type);
    }
}
