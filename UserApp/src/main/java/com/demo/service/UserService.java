package com.demo.service;

import com.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public User createUser(User newUser) throws Exception {
        if (!users.isEmpty()) {
            for (User user : users) {
                String email = user.getEmail();
                if (email.equals(newUser.getEmail())) {
                    throw new Exception("User already exists");
                }
            }
        }
        users.add(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserForUsername(String username) {
        for (User user : users) {
            if (user.getEmail().equals(username)) {
                return user;
            }
        }
        throw new NoSuchElementException("User not found");
    }
}
