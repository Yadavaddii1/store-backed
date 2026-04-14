package com.wheat_store.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Customer user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item); 
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Customer getUser() { return user; }
    public void setUser(Customer user) { this.user = user; }

    public List<CartItem> getItems() { return items;}
    public void setItems(List<CartItem> items) { this.items = items;}

}