package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource_requests")
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private String status; // e.g. PENDING, APPROVED, REJECTED

    @Column(nullable = false)
    private LocalDateTime requestedAt = LocalDateTime.now();

    private LocalDateTime processedAt; // optional, when request is approved/rejected

    public ResourceRequest() {}

    public ResourceRequest(User user, Resource resource, String status) {
        this.user = user;
        this.resource = resource;
        this.status = status;
        this.requestedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Resource getResource() { return resource; }

    public void setResource(Resource resource) { this.resource = resource; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getRequestedAt() { return requestedAt; }

    public void setRequestedAt(LocalDateTime requestedAt) { this.requestedAt = requestedAt; }

    public LocalDateTime getProcessedAt() { return processedAt; }

    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
}
