package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {

	private final ResourceRequestService requestService;

	public ResourceRequestController(ResourceRequestService requestService) {
		this.requestService = requestService;
	}

	@PostMapping
	public ResponseEntity<ResourceRequest> createRequest(@RequestParam Long userId) {
		ResourceRequest created = requestService.createRequest(userId);
		return ResponseEntity.ok(created);
	}

	@GetMapping
	public ResponseEntity<List<ResourceRequest>> getAllRequests() {
		return ResponseEntity.ok(requestService.getAllRequests());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResourceRequest> getRequestById(@PathVariable Long id) {
		return ResponseEntity.ok(requestService.getRequestById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
		requestService.deleteRequest(id);
		return ResponseEntity.ok("Request deleted successfully");
	}
}
