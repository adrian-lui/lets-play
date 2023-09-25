package com.adrianlui.letsplay.controllers;

import com.adrianlui.letsplay.requests.AddUserRequest;
import com.adrianlui.letsplay.responses.UserResponse;
import com.adrianlui.letsplay.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

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
}
