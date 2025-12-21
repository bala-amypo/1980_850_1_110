package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.Resource;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public ResourceRequestService(ResourceRequestRepository requestRepository,
                                  UserRepository userRepository,
                                  ResourceRepository resourceRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    // Get all requests
    public List<ResourceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // Get request by ID
    public ResourceRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Request not found with id: " + id));
    }

    // Create new request
    public ResourceRequest createRequest(Long userId, Long resourceId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));

        ResourceRequest request = new ResourceRequest(user, resource, "PENDING");
        request.setRequestedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    // Update request status (APPROVED / REJECTED)
    public ResourceRequest updateRequestStatus(Long id, String status) {
        ResourceRequest request = getRequestById(id);
        request.setStatus(status);
        request.setProcessedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    // Delete request
    public void deleteRequest(Long id) {
        ResourceRequest request = getRequestById(id);
        requestRepository.delete(request);
    }
}
