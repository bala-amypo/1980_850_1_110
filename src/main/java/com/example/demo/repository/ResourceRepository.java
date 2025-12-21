package com.example.demo.repository;

import com.example.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    // Find resource by name
    Optional<Resource> findByResourceName(String resourceName);

    // Find resources by type
    List<Resource> findByResourceType(String resourceType);

    // Find available resources
    List<Resource> findByAvailableTrue();
}
