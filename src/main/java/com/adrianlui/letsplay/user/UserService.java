package com.adrianlui.letsplay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<UserResponse> allUsers() {
        return userRepository.findAll().stream().map(user ->  new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole())).collect(Collectors.toList());
    }

    public void addUser(AddUserRequest addUserRequest) {
        User user = new User(addUserRequest.getName(), addUserRequest.getEmail(), addUserRequest.getPassword(), addUserRequest.getRole());
        userRepository.save(user);
        System.out.println(user.getName() + " is saved.");
    }
}
