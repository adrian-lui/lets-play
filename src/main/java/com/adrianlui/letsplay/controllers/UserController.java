package com.adrianlui.letsplay.controllers;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.requests.AddUserRequest;
import com.adrianlui.letsplay.requests.LoginRequest;
import com.adrianlui.letsplay.requests.UpdateRequest;
import com.adrianlui.letsplay.responses.UserResponse;
import com.adrianlui.letsplay.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.allUsers(),
                                    HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody AddUserRequest addUserRequest) {
        userService.addUser(addUserRequest);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        if (userService.loginUser(loginRequest)) {
            System.out.println(loginRequest.getEmail() + " is authenticated");
        } else {
            System.out.println(loginRequest.getEmail() + " is not authenticated");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
                    HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") String id, @RequestBody UpdateRequest updateRequest) {
        if (userService.updateUserById(id, updateRequest)) {
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User updating not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
