package com.adrianlui.letsplay.services;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.domain.requests.AddProductRequest;
import com.adrianlui.letsplay.domain.requests.UpdateProductRequest;
import com.adrianlui.letsplay.repositories.ProductRepository;
import com.adrianlui.letsplay.repositories.UserRepository;
import com.adrianlui.letsplay.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public String addProduct(AddProductRequest addProductRequest) {
        if (addProductRequest.getName() == null || addProductRequest.getDescription() == null || addProductRequest.getUserId() == null || addProductRequest.getPrice() == null) {
            return "INCOMPLETE";
        }
        if (userRepository.findById(addProductRequest.getUserId()).isEmpty()) {
            return "INVALID_OWNER";
        }
        Product product = Product.builder()
                                 .name(addProductRequest.getName())
                                 .description(addProductRequest.getDescription())
                                 .price(addProductRequest.getPrice())
                                 .userId(addProductRequest.getUserId())
                                 .build();
        productRepository.save(product);
        System.out.println(product.getName() + " is saved.");
        return "SUCCESS";
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean updateProductById(String id, UpdateProductRequest updateProductRequest) {
        if (updateProductRequest.getDescription() == null && updateProductRequest.getName() == null && updateProductRequest.getUserId() == null && updateProductRequest.getPrice() == null) {
            throw new InvalidParameterException("Invalid request body");
        }
        Optional<Product> toUpdate = productRepository.findById(id);
        if (toUpdate.isPresent()) {
            Product updatingProduct = toUpdate.get();
            if (updateProductRequest.getDescription() != null) {
                updatingProduct.setDescription(updateProductRequest.getDescription());
            }
            if (updateProductRequest.getName() != null) {
                updatingProduct.setName(updateProductRequest.getName());
            }
            if (updateProductRequest.getDescription() != null) {
                updatingProduct.setDescription(updateProductRequest.getDescription());
            }
            if (updateProductRequest.getPrice() != null) {
                updatingProduct.setPrice(updateProductRequest.getPrice());
            }
            if (updateProductRequest.getUserId() != null) {
                if (userRepository.findById(updateProductRequest.getUserId()).isEmpty()) {
                    throw new UsernameNotFoundException("User id not found");
                }
                updatingProduct.setUserId(updateProductRequest.getUserId());
            }
            productRepository.save(updatingProduct);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProductById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Product> findProductByUserId(String id) {
        if (userRepository.findById(id).isEmpty()) {
            return null;
        }
        return productRepository.findProductsByUserId(id).orElse(null);
    }
}
