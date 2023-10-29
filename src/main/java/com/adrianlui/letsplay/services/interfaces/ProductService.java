package com.adrianlui.letsplay.services.interfaces;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.domain.requests.AddProductRequest;
import com.adrianlui.letsplay.domain.requests.UpdateProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    String addProduct(AddProductRequest addProductRequest);

    Optional<Product> findProductById(String id);

    boolean updateProductById(String id, UpdateProductRequest updateProductRequest);

    boolean deleteProductById(String id);

    List<Product> findProductByUserId(String id);
}
