package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource_allocations")
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private ResourceRequest request;

    @Column(nullable = false)
    private LocalDateTime allocatedAt = LocalDateTime.now();

    private LocalDateTime releasedAt; // optional, when resource is freed

    public ResourceAllocation() {}

    public ResourceAllocation(Resource resource, User user, ResourceRequest request) {
        this.resource = resource;
        this.user = user;
        this.request = request;
        this.allocatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Resource getResource() { return resource; }

    public void setResource(Resource resource) { this.resource = resource; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public ResourceRequest getRequest() { return request; }

    public void setRequest(ResourceRequest request) { this.request = request; }

    public LocalDateTime getAllocatedAt() { return allocatedAt; }

    public void setAllocatedAt(LocalDateTime allocatedAt) { this.allocatedAt = allocatedAt; }

    public LocalDateTime getReleasedAt() { return releasedAt; }

    public void setReleasedAt(LocalDateTime releasedAt) { this.releasedAt = releasedAt; }
}
