package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.Resource;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public ResourceRequestServiceImpl(ResourceRequestRepository requestRepository,
                                      UserRepository userRepository,
                                      ResourceRepository resourceRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public List<ResourceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public ResourceRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Request not found with id: " + id));
    }

    @Override
    public ResourceRequest createRequest(Long userId, Long resourceId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));

        ResourceRequest request = new ResourceRequest(user, resource, "PENDING");
        request.setRequestedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    @Override
    public ResourceRequest updateRequestStatus(Long id, String status) {
        ResourceRequest request = getRequestById(id);
        request.setStatus(status);
        request.setProcessedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {
        ResourceRequest request = getRequestById(id);
        requestRepository.delete(request);
    }
}
