package com.adrianlui.letsplay.controllers;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.domain.requests.UpdateRequest;
import com.adrianlui.letsplay.domain.responses.UserResponse;
import com.adrianlui.letsplay.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    private UserResponse userResponseMapper(User user) {
        return UserResponse.builder().username(user.getUsername())
                           .email(user.getEmail())
                           .role(user.getRole().name())
                           .id(user.getId()).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(@AuthenticationPrincipal Jwt principal) {
        System.out.println(principal.getClaims().toString());
        List<UserResponse> cleanList = userService.findAllUsers().stream()
                                                  .map(this::userResponseMapper).toList();
        return new ResponseEntity<>(cleanList,
                                    HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Validated @RequestBody User addUserRequest) {
        userService.addUser(addUserRequest);
        return new ResponseEntity<>("User %s created successfully".formatted(addUserRequest.getUsername()),
                                    HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(userResponseMapper(user.get()),
                                        HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("User id not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@Validated @PathVariable("id") String id, @RequestBody UpdateRequest updateRequest) {
        if (userService.updateUserById(id,
                                       updateRequest)) {
            return new ResponseEntity<>("User updated successfully",
                                        HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("User updating not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>("User deleted successfully",
                                        HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
