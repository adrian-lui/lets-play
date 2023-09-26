package com.adrianlui.letsplay.controllers;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.requests.AddProductRequest;
import com.adrianlui.letsplay.requests.UpdateProductRequest;
import com.adrianlui.letsplay.responses.ProductResponse;
import com.adrianlui.letsplay.services.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.allProducts(),
                HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody AddProductRequest addProductRequest) {
        productService.addProduct(addProductRequest);
    }

//    @PostMapping("/login")
//    public void login(@RequestBody LoginRequest loginRequest) {
//        if (productService.loginProduct(loginRequest)) {
//            System.out.println(loginRequest.getEmail() + " is authenticated");
//        } else {
//            System.out.println(loginRequest.getEmail() + " is not authenticated");
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
                    HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable("id") String id, @RequestBody UpdateProductRequest updateProductRequest) {
        if (productService.updateProductById(id, updateProductRequest)) {
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product updating not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") String id) {
        if (productService.deleteProductById(id)) {
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
        }
    }
}
