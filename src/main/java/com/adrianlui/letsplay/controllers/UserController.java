package com.adrianlui.letsplay.controllers;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.domain.requests.UpdateRequest;
import com.adrianlui.letsplay.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@AuthenticationPrincipal Jwt principal) {
        System.out.println(principal.getClaims().toString());
        return new ResponseEntity<>(userService.findAllUsers(),
                                    HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody User addUserRequest) {
        if (!userService.addUser(addUserRequest)) {
            return new ResponseEntity<>("Invalid user details submitted", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User %s created successfully".formatted(addUserRequest.getUsername()),
                                    HttpStatus.CREATED);
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
        if (userService.updateUserById(id,
                                       updateRequest)) {
            return new ResponseEntity<>("User updated successfully",
                                        HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User updating not found",
                                        HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>("User deleted successfully",
                                        HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found",
                                        HttpStatus.BAD_REQUEST);
        }
    }
}
