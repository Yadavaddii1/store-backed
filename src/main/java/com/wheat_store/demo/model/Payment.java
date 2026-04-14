package com.wheat_store.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    private String paymentId; // from gateway
    private String status; // SUCCESS, FAILED

    private String method; // UPI, CARD

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters setters

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}

    public String getPaymentId() {return paymentId;}
    public void setPaymentId(String paymentId) {this.paymentId = paymentId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getMethod() {return method;}
    public void setMethod(String method) {this.method = method;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;}
}