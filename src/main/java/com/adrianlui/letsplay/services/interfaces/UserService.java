package com.adrianlui.letsplay.services.interfaces;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.requests.AddUserRequest;
import com.adrianlui.letsplay.requests.LoginRequest;
import com.adrianlui.letsplay.requests.UpdateRequest;
import com.adrianlui.letsplay.responses.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDetailsService userDetailsService();
    List<UserResponse> allUsers();
    void addUser(AddUserRequest addUserRequest);
    Optional<User> findUserById(String id);
    boolean updateUserById(String id, UpdateRequest updateRequest);

    boolean deleteUserById(String id);
}
