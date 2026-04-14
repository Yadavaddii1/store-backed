package com.wheat_store.demo.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private ProductVariant productVariant;

    private int quantity;

    private double price;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}

    public ProductVariant getProductVariant() {return productVariant;}
    public void setProductVariant(ProductVariant productVariant) {this.productVariant = productVariant;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
}