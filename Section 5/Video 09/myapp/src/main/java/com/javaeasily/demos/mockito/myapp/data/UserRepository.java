package com.javaeasily.demos.mockito.myapp.data;   // aka data or persistence layer

import com.javaeasily.demos.mockito.myapp.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    // Using just a Map for demo purposes
    // Production would have a Relational DataBase System
    // e.g. JDBC logic to access MySQL or H2
    private Map<String, User> users = new HashMap<String, User>();

    public UserRepository() {
        users.put("matt", new User("matt", "letmein"));
        users.put("frank", new User("frank", "myPassword"));
    }

    public User findByUsername(String username) {
        return users.get(username);
    }
}
