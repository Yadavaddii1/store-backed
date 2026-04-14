package com.wheat_store.demo.controller;

import com.wheat_store.demo.model.*;
import com.wheat_store.demo.repository.*;
import com.wheat_store.demo.DTO.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {

        for (ProductVariant variant : product.getVariants()) {
            variant.setProduct(product);
        }

        Product saved = productRepository.save(product);
        ApiResponse<Product> body =
                ApiResponse.success("Product created successfully", saved);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
