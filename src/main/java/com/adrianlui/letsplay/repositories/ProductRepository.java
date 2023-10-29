package com.adrianlui.letsplay.repositories;

import com.adrianlui.letsplay.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<List<Product>> findProductsByUserId(String userId);
}
