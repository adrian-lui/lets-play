package com.adrianlui.letsplay.controllers;

import com.adrianlui.letsplay.domain.Product;
import com.adrianlui.letsplay.domain.requests.AddProductRequest;
import com.adrianlui.letsplay.domain.requests.UpdateProductRequest;
import com.adrianlui.letsplay.services.ProductServiceImpl;
import com.adrianlui.letsplay.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(),
                                    HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Product>> getProductByUserId(@RequestParam String userid) {
        List<Product> products = productService.findProductByUserId(userid);
        if (products == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@Validated @RequestBody AddProductRequest addProductRequest) {
        String result = productService.addProduct(addProductRequest);
        if (result.equals("INCOMPLETE")) {
            return new ResponseEntity<>("Invalid product submitted", HttpStatus.BAD_REQUEST);
        } else if (result.equals("INVALID_OWNER")) {
            return new ResponseEntity<>("Product user id not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Product added %s".formatted(addProductRequest.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        Optional<Product> product = productService.findProductById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
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
