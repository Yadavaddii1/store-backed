package com.wheat_store.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wheat_store.demo.model.Cart;
import com.wheat_store.demo.model.Customer;
import com.wheat_store.demo.model.Order;
import com.wheat_store.demo.model.Product;
import com.wheat_store.demo.repository.CartRepository;
import com.wheat_store.demo.repository.OrderRepository;
import com.wheat_store.demo.repository.ProductRepository;
import com.wheat_store.demo.repository.customerRepository;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private customerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // USERS
    @GetMapping("/users")
    public List<Customer> getUsers() {
        return customerRepository.findAll();
    }

    // ORDERS
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // CARTS
    @GetMapping("/carts")
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    // PRODUCTS
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {

    Map<String, Object> stats = new HashMap<>();

    stats.put("users", customerRepository.count());
    stats.put("orders", orderRepository.count());
    stats.put("products", productRepository.count());

    Double revenue = orderRepository.findAll()
        .stream()
        .mapToDouble(o -> o.getTotalAmount() != null ? o.getTotalAmount() : 0)
        .sum();

    stats.put("revenue", revenue);

    return stats;
    }

}
    
