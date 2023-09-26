package com.adrianlui.letsplay.repositories;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
