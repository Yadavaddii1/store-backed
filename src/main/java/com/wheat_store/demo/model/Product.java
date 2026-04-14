package com.wheat_store.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(length = 1000)
    private String description;

    private String brand;

    private Double rating;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();

    // getters & setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getProductName() { return name; }
    public void setProductName(String name) { this.name = name; }

    public String getProductDescription() { return description; }
    public void setProductDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
   
    public List<ProductVariant> getVariants() { return variants;}
    public void setVariants(List<ProductVariant> variants) { this.variants = variants;}

}
