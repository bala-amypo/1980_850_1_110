package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

	public List<User> getAllUsers();

	public User getUser(Long id);

	public User registerUser(User user);

}
