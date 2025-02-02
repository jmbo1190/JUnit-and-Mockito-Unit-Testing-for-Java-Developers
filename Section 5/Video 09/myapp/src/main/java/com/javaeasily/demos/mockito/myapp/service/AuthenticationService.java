package com.javaeasily.demos.mockito.myapp.service;   // aka Business or Service layer

import com.javaeasily.demos.mockito.myapp.User;
import com.javaeasily.demos.mockito.myapp.data.UserRepository;

public class AuthenticationService {

    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user.getPassword().equals(password);
    }
}
