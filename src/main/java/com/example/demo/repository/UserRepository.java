package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used for login/authentication)
    Optional<User> findByEmail(String email);

    // Find users by role (e.g. ADMIN, USER)
    List<User> findByRole(String role);

    // Find active users
    List<User> findByActiveTrue();
}
