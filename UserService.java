package com.example.demo;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    private int callCount;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.callCount = 0;
    }

    public Optional<User> findById(Long id) {
        callCount++;
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        callCount++;
        return userRepository.findByEmail(email);
    }

    public int getCallCount() {
        return callCount;
    }
}

