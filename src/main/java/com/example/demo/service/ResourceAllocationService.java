package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ResourceAllocation;

public interface ResourceAllocationService {

	public List<ResourceAllocation> getAllAllocations();

	public ResourceAllocation getAllocationById(Long id);

	public ResourceAllocation allocateResource(Long resourceId, Long userId, Long requestId);

	public ResourceAllocation releaseResource(Long allocationId);

	public void deleteAllocation(Long id);
}
