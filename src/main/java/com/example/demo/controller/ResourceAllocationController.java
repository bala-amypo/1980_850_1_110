package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

	private final ResourceAllocationRepository allocationRepository;

	public ResourceAllocationServiceImpl(ResourceAllocationRepository allocationRepository,
			ResourceRepository resourceRepository, ResourceRequestRepository requestRepository) {
		this.allocationRepository = allocationRepository;

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
	public ResourceAllocation autoAllocate(Long requestId) {
		ResourceAllocation resource = allocationRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + requestId));

		return allocationRepository.save(resource);
	}

}
