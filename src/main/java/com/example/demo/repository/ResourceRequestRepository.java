package com.example.demo.repository;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {

    // Find requests by user
    List<ResourceRequest> findByUser(User user);

    // Find requests by resource
    List<ResourceRequest> findByResource(Resource resource);

    // Find requests by status (e.g. PENDING, APPROVED, REJECTED)
    List<ResourceRequest> findByStatus(String status);
}
