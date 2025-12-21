package com.example.demo.repository;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.Resource;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation, Long> {

    // Find allocations by user
    List<ResourceAllocation> findByUser(User user);

    // Find allocations by resource
    List<ResourceAllocation> findByResource(Resource resource);

    // Find active allocations (releasedAt is null)
    List<ResourceAllocation> findByReleasedAtIsNull();
}
