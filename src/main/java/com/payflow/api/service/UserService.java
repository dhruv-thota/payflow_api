package com.payflow.api.service;

import com.payflow.api.entity.User;
import com.payflow.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    /*
     * Spring Startup & Dependency Injection Explanation:
     * @Service marks UserService as a Spring-managed component (bean) during component scanning.
     * Spring Data JPA creates a dynamic proxy implementation of UserRepository at startup.
     * Spring's Dependency Injection (DI) mechanism detects the @Autowired annotation and automatically
     * injects the UserRepository bean into this field when the application context initializes.
     */
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUpiId(String upiId) {
        return userRepository.findByUpiId(upiId);
    }

    public List<User> getUsersWithBalanceGreaterThan(Double amount) {
        return userRepository.findUsersWithBalanceGreaterThan(amount);
    }
}
