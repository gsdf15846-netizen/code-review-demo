package com.example.demo;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    private int callCount;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.callCount = 0;
    }

    // CHANGE 1: 签名改了,从 findById(Long) 变成 findById(Long, boolean)
    public Optional<User> findById(Long id, boolean includeDeleted) {
        callCount++;
        if (includeDeleted) {
            return userRepository.findByIdIncludingDeleted(id);
        }
        return userRepository.findById(id);
    }

    // CHANGE 2: 新方法,有 NPE 风险
    public String getUserDisplayName(Long id) {
        Optional<User> user = userRepository.findById(id);
        // BUG: 没判空就调 .get(),空 Optional 时抛 NoSuchElementException
        return user.get().getName().toUpperCase();
    }

    public Optional<User> findByEmail(String email) {
        callCount++;
        return userRepository.findByEmail(email);
    }

    public int getCallCount() {
        return callCount;
    }
}
