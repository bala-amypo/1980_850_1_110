package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ResourceAllocation;

public interface ResourceAllocationService {

	public List<ResourceAllocation> getAllAllocations();

	public ResourceAllocation getAllocationById(Long id);

	public ResourceAllocation autoAllocate(Long requestId);


}
