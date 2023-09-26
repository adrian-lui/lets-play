package com.adrianlui.letsplay.repositories;

import com.adrianlui.letsplay.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByEmail(String username);
//    Optional<User> findByEmail(String email);

}
