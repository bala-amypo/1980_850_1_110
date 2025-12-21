package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Resource;

public interface ResourceService {

	public List<Resource> getAllResources();

	public Resource getResourceById(Long id);

	public Resource createResource(Resource resource);

	public Resource updateResource(Long id, Resource updatedResource);

	public void deleteResource(Long id);

	public List<Resource> getAvailableResources();

	public List<Resource> getResourcesByType(String type);

}
