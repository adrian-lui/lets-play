package com.adrianlui.letsplay.services;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.repositories.UserRepository;
import com.adrianlui.letsplay.requests.AddUserRequest;
import com.adrianlui.letsplay.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> allUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(user -> UserResponse.builder()
                                                      .id(user.getId())
                                                      .name(user.getName())
                                                      .email(user.getEmail())
                                                      .role(user.getRole())
                                                      .build())
                             .collect(Collectors.toList());
    }

    public void addUser(AddUserRequest addUserRequest) {
        User user = User.builder()
                        .name(addUserRequest.getName())
                        .email(addUserRequest.getEmail())
                        .password(addUserRequest.getPassword())
                        .role(addUserRequest.getRole())
                        .build();
        userRepository.save(user);
        System.out.println(user.getName() + " is saved.");
    }
}
