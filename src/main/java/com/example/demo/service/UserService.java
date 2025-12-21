package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	  
	public  User getUserById(Long id);
	  
	public User getUserByEmail(String email);
	
	 public User createUser(User user);
	 
	 public User updateUser(Long id, User updatedUser);
	 
	 public void deleteUser(Long id);
	 
	 public List<User> getActiveUsers();
	 
	 public List<User> getUsersByRole(String role);
}
