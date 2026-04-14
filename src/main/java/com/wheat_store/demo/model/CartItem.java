package com.wheat_store.demo.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariant variant;

    private Integer quantity;

    // getters & setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }

    public ProductVariant getVariant() { return variant;}
    public void setVariant(ProductVariant variant) { this.variant = variant;}

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    
}