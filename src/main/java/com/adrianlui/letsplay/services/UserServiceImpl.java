package com.adrianlui.letsplay.services;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.repositories.UserRepository;
import com.adrianlui.letsplay.requests.AddUserRequest;
import com.adrianlui.letsplay.requests.LoginRequest;
import com.adrianlui.letsplay.requests.UpdateRequest;
import com.adrianlui.letsplay.responses.UserResponse;
import com.adrianlui.letsplay.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username);
    }

    @Override
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

    @Override
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

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean updateUserById(String id, UpdateRequest updateRequest) {
        Optional<User> toUpdate = userRepository.findById(id);
        if (toUpdate.isPresent()) {
            User updatingUser = toUpdate.get();
            if (updateRequest.getEmail() != null) {
                updatingUser.setEmail(updateRequest.getEmail());
            }
            if (updateRequest.getName() != null) {
                updatingUser.setName(updateRequest.getName());
            }
            if (updateRequest.getPassword() != null) {
                updatingUser.setPassword(updateRequest.getPassword());
            }
            if (updateRequest.getRole() != null) {
                updatingUser.setRole(updateRequest.getRole());
            }
            userRepository.save(updatingUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
