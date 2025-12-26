package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {
    
    private final ResourceRequestRepository reqRepo;
    private final UserRepository userRepo;
    
    public ResourceRequestServiceImpl(ResourceRequestRepository reqRepo, UserRepository userRepo) {
        this.reqRepo = reqRepo;
        this.userRepo = userRepo;
    }
    
    @Override
    public ResourceRequest createRequest(Long userId, ResourceRequest request) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (request.getStartTime() != null && request.getEndTime() != null && 
            !request.getStartTime().isBefore(request.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        
        request.setRequestedBy(user);
        if (request.getStatus() == null) {
            request.setStatus("PENDING");
        }
        
        return reqRepo.save(request);
    }
    
    @Override
    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return reqRepo.findByRequestedBy_Id(userId);
    }
    
    @Override
    public ResourceRequest getRequest(Long id) {
        return reqRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
    }
    
    @Override
    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest request = getRequest(requestId);
        request.setStatus(status);
        return reqRepo.save(request);
    }
}