package com.wheat_store.demo.service;

import com.wheat_store.demo.model.*;
import com.wheat_store.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Customer user, String address, Double latitude, Double longitude) {

        
        if (address == null || address.trim().isEmpty()) {
            throw new RuntimeException("Address is required");
        }

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setStatus("CONFIRMED");

        order.setLatitude(latitude);
        order.setLongitude(longitude);

        logger.info("Saved Latitude in Order: {}", order.getLatitude());
        logger.info("Saved Longitude in Order: {}", order.getLongitude());


        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProductVariant(item.getVariant());
            oi.setQuantity(item.getQuantity());
            oi.setPrice(item.getVariant().getPrice());

            total += item.getQuantity() * item.getVariant().getPrice();
            orderItems.add(oi);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        return savedOrder;
    }

    public List<Order> getOrdersByUser(Customer user) {
        
        return orderRepository.findByUser(user);
    }
    
}