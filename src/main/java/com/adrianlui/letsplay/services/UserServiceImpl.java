package com.adrianlui.letsplay.services;

import com.adrianlui.letsplay.domain.Role;
import com.adrianlui.letsplay.domain.User;
import com.adrianlui.letsplay.domain.requests.UpdateRequest;
import com.adrianlui.letsplay.repositories.UserRepository;
import com.adrianlui.letsplay.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(User addUserRequest) {
        if (addUserRequest.getUsername() == null || addUserRequest.getEmail() == null || addUserRequest.getPassword() == null) {
            return false;
        }
        addUserRequest.setPassword(passwordEncoder().encode(addUserRequest.getPassword()));
        addUserRequest.setRole(Role.USER);
        userRepository.save(addUserRequest);
        System.out.printf("New user: %s is saved%n", addUserRequest.getUsername());
        return true;
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean updateUserById(String id, UpdateRequest updateRequest) {
        Optional<User> toUpdate = userRepository.findById(id);
        if (toUpdate.isPresent()) {
            User updatingUser = toUpdate.get();
            if (updateRequest.getEmail() != null) {
                updatingUser.setEmail(updateRequest.getEmail());
            }
            if (updateRequest.getUsername() != null) {
                updatingUser.setUsername(updateRequest.getUsername());
            }
            if (updateRequest.getPassword() != null) {
                updatingUser.setPassword(passwordEncoder().encode(updateRequest.getPassword()));
            }
            if (updateRequest.getRole() != null) {
                updatingUser.setRole(updateRequest.getRole());
            }
            userRepository.save(updatingUser);
            System.out.printf("Updated user %s%n", updateRequest);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            System.out.printf("Deleted user %s%n", id);
            return true;
        }
        return false;
    }
}
