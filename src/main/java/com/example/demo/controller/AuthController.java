package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User request) {
		User response = userService.registerUser(request);
		return ResponseEntity.ok(response);
	}

	/*
	 * @PostMapping("/login") public ResponseEntity<AuthResponse> login(@RequestBody
	 * AuthRequest request) { AuthResponse response = userService.login(request);
	 * return ResponseEntity.ok(response); }
	 */
}
