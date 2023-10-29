package com.adrianlui.letsplay.services.interfaces;

import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.domain.requests.UpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    UserDetailsService userDetailsService();

    List<User> findAllUsers();

    boolean addUser(User addUserRequest);

    Optional<User> findUserById(String id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    boolean updateUserById(String id, UpdateRequest updateRequest);

    boolean deleteUserById(String id);
}
