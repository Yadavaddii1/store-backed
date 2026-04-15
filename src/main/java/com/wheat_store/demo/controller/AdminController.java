package com.wheat_store.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheat_store.demo.DTO.OrderItemDTO;
import com.wheat_store.demo.DTO.OrderResponse;
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

    @GetMapping("/orders")
    public Map<String, Object> getOrders() {

    List<Order> orders = orderRepository.findAll();

    List<OrderResponse> response = orders.stream().map(order -> {

        OrderResponse dto = new OrderResponse();

        // ✅ BASIC INFO
        dto.setOrderId(order.getId());
        dto.setAddress(order.getAddress());
        dto.setLatitude(order.getLatitude());
        dto.setLongitude(order.getLongitude());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        // ✅ CUSTOMER INFO
        dto.setCustomerName(order.getUser().getName());
        dto.setPhone(order.getUser().getPhoneNumber());

        // ✅ ITEMS
        List<OrderItemDTO> items = order.getItems().stream().map(item -> {

            OrderItemDTO itemDTO = new OrderItemDTO();

            itemDTO.setProductName(
                item.getProductVariant().getProduct().getProductName()
            );
            itemDTO.setWeight(
                item.getProductVariant().getWeight()
            );
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQuantity(item.getQuantity());

            return itemDTO;

        }).toList();

        dto.setItems(items);

        return dto;

    }).toList();

    return Map.of(
        "success", true,
        "message", "Orders fetched successfully",
        "data", response
    );
}

}
    
