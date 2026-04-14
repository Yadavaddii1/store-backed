package com.wheat_store.demo.service;

import com.wheat_store.demo.model.*;
import com.wheat_store.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment processPayment(Long orderId, String method) {
        if (method == null || method.trim().isEmpty()) {
            throw new RuntimeException("Payment method is required");
        }

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(method);
        payment.setStatus("SUCCESS");
        payment.setPaymentId(UUID.randomUUID().toString());

        order.setStatus("PAID");
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }
}