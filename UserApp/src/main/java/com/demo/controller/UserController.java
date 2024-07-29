package com.demo.controller;

import com.demo.model.User;
import com.demo.response.UserResponse;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> currentUser(@RequestBody User newUser) {
        UserResponse userResponse = new UserResponse();

        try {
            User createdUser = userService.createUser(newUser);
            userResponse.setMessage("User created successfully");
            userResponse.setUser(createdUser);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            userResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        UserResponse userResponse = new UserResponse();
        try {
            userResponse.setMessage("User get successfully");
            userResponse.setUser(userService.getUserForUsername(username));
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            userResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String username) {
        UserResponse userResponse = new UserResponse();
        try {
            userService.deleteUser(username);
            userResponse.setMessage("User deleted successfully");
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            userResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
