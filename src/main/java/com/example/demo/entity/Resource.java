package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String resourceName;   // e.g. "Laptop", "Projector"

    @Column(nullable = false)
    private String resourceType;   // e.g. "EQUIPMENT", "ROOM"

    @Column(nullable = false)
    private boolean available = true; // default availability

    private String description; // optional details

    public Resource() {}

    public Resource(String resourceName, String resourceType, boolean available, String description) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.available = available;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getResourceName() { return resourceName; }

    public void setResourceName(String resourceName) { this.resourceName = resourceName; }

    public String getResourceType() { return resourceType; }

    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
