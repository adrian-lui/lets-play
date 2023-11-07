package com.adrianlui.letsplay.services;

import com.adrianlui.letsplay.domain.security.SecurityUser;
import com.adrianlui.letsplay.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = new SecurityUser(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
        System.out.println(user.toString());
        return user;
    }
}
