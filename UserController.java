package com.example.demo;

import java.util.Optional;

public class UserController extends BaseController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String getUser(Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(User::getName).orElse("unknown");
    }

    public String getUserByEmail(String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(User::getName).orElse("unknown");
    }

    @Override
    public String getEndpointName() {
        return "user";
    }
}
