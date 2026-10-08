package com.payflow.api.controller;

import com.payflow.api.entity.User;
import com.payflow.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /users - Register a new user
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // GET /users - Retrieve all registered users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // GET /users/{id} - Retrieve user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /users/upi/{upiId} - Retrieve user by UPI ID
    @GetMapping("/upi/{upiId}")
    public ResponseEntity<User> getUserByUpiId(@PathVariable String upiId) {
        Optional<User> userOptional = userService.findByUpiId(upiId);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /users/high-balance?amount={amount} - Retrieve users with balance greater than supplied amount
    @GetMapping("/high-balance")
    public ResponseEntity<List<User>> getUsersWithBalanceGreaterThan(@RequestParam Double amount) {
        List<User> users = userService.getUsersWithBalanceGreaterThan(amount);
        return ResponseEntity.ok(users);
    }
}
