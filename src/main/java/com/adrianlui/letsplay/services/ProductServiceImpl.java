package com.adrianlui.letsplay.services;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.repositories.ProductRepository;
import com.adrianlui.letsplay.requests.AddProductRequest;
import com.adrianlui.letsplay.requests.UpdateProductRequest;
import com.adrianlui.letsplay.responses.ProductResponse;
import com.adrianlui.letsplay.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> allProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .userId(product.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(AddProductRequest addProductRequest) {
        Product product = Product.builder()
                .name(addProductRequest.getName())
                .description(addProductRequest.getDescription())
                .price(addProductRequest.getPrice())
                .userId(addProductRequest.getUserId())
                .build();
        productRepository.save(product);
        System.out.println(product.getName() + " is saved.");
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean updateProductById(String id, UpdateProductRequest updateProductRequest) {
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
            if (updateProductRequest.getPrice() != 0) {
                updatingProduct.setPrice(updateProductRequest.getPrice());
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
}
