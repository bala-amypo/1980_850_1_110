package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ResourceRequest;

public interface ResourceRequestService {

	public List<ResourceRequest> getAllRequests();

	public ResourceRequest getRequestById(Long id);

	public ResourceRequest createRequest(Long userId, Long resourceId);

	public ResourceRequest updateRequestStatus(Long id, String status);

	public void deleteRequest(Long id);

}
