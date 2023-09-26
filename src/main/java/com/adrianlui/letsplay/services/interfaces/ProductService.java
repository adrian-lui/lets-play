package com.adrianlui.letsplay.services.interfaces;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.requests.AddProductRequest;
import com.adrianlui.letsplay.requests.UpdateProductRequest;
import com.adrianlui.letsplay.responses.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> allProducts();

    void addProduct(AddProductRequest addProductRequest);

    Optional<Product> findProductById(String id);

    boolean updateProductById(String id, UpdateProductRequest updateProductRequest);

    boolean deleteProductById(String id);
}
