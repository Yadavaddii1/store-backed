package com.wheat_store.demo.controller;

import com.wheat_store.demo.model.Product;
import com.wheat_store.demo.repository.ProductRepository;

import com.wheat_store.demo.DTO.ApiResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Product>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAll(pageable);

        ApiResponse<Page<Product>> body =
                ApiResponse.success("Products fetched successfully", products);
        return new ResponseEntity<>(body, HttpStatus.OK);
}

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ApiResponse<Product> body =
                ApiResponse.success("Product fetched successfully", product);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> searchProducts(
        @RequestParam String name) {

    List<Product> products = productRepository.findByNameContainingIgnoreCase(name);

    ApiResponse<List<Product>> body =
            ApiResponse.success("Products fetched successfully", products);

    return new ResponseEntity<>(body, HttpStatus.OK);
}
}
