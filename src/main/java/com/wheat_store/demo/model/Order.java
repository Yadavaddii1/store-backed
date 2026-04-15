package com.wheat_store.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders") // ✅ FIX HERE
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Customer user;

    private Double totalAmount;

    private String status; // CREATED, PAID, FAILED

    private String address;

    private Double latitude;

    private Double longitude;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public Customer getUser() {return user;}
    public void setUser(Customer user) {this.user = user;}

    public Double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(Double totalAmount) {this.totalAmount = totalAmount;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public Double getLatitude() {return latitude;}
    public void setLatitude(Double latitude) {this.latitude = latitude;}

    public Double getLongitude() {return longitude;}
    public void setLongitude(Double longitude) {this.longitude = longitude;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public List<OrderItem> getItems() {return items;}
    public void setItems(List<OrderItem> items) { this.items = items;}

    
}