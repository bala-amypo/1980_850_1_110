package com.example.demo.service.implementation;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.Resource;
import com.example.demo.entity.User;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    private final ResourceAllocationRepository allocationRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final ResourceRequestRepository requestRepository;

    public ResourceAllocationServiceImpl(ResourceAllocationRepository allocationRepository,
                                         ResourceRepository resourceRepository,
                                         UserRepository userRepository,
                                         ResourceRequestRepository requestRepository) {
        this.allocationRepository = allocationRepository;
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    @Override
    public ResourceAllocation getAllocationById(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Allocation not found with id: " + id));
    }

    @Override
    public ResourceAllocation allocateResource(Long resourceId, Long userId, Long requestId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));

        if (!resource.isAvailable()) {
            throw new IllegalStateException("Resource is not available for allocation");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        ResourceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + requestId));

        ResourceAllocation allocation = new ResourceAllocation(resource, user, request);
        resource.setAvailable(false); // mark resource as allocated
        resourceRepository.save(resource);

        return allocationRepository.save(allocation);
    }

    @Override
    public ResourceAllocation releaseResource(Long allocationId) {
        ResourceAllocation allocation = getAllocationById(allocationId);
        allocation.setReleasedAt(LocalDateTime.now());

        Resource resource = allocation.getResource();
        resource.setAvailable(true); // mark resource as available again
        resourceRepository.save(resource);

        return allocationRepository.save(allocation);
    }

    @Override
    public void deleteAllocation(Long id) {
        ResourceAllocation allocation = getAllocationById(id);
        allocationRepository.delete(allocation);
    }
}
