package com.wheat_store.demo.controller;

import com.wheat_store.demo.DTO.ApiResponse;
import com.wheat_store.demo.DTO.CheckoutRequest;
import com.wheat_store.demo.DTO.OrderItemDTO;
import com.wheat_store.demo.DTO.OrderResponse;
import com.wheat_store.demo.DTO.PaymentRequest;
import com.wheat_store.demo.DTO.PaymentResponse;
import com.wheat_store.demo.model.Customer;
import com.wheat_store.demo.model.Order;
import com.wheat_store.demo.model.OrderItem;
import com.wheat_store.demo.model.Payment;
import com.wheat_store.demo.security.CustomUserDetails;
import com.wheat_store.demo.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<OrderResponse>> checkout(
        @RequestBody CheckoutRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

    Customer user = userDetails.getCustomer();
    Order order = orderService.createOrder(user, request.getAddress(), request.getLatitude(), request.getLongitude());
    OrderResponse response = toOrderResponse(order);

    ApiResponse<OrderResponse> body = ApiResponse.success("Order created successfully", response);
    return new ResponseEntity<>(body, HttpStatus.CREATED);
     }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getMyOrders(
        @AuthenticationPrincipal CustomUserDetails userDetails) {

    Customer user = userDetails.getCustomer();

    List<Order> orders = orderService.getOrdersByUser(user);
    List<OrderResponse> response = orders.stream().map(this::toOrderResponse).toList();
    return ResponseEntity.ok(
            ApiResponse.success("Orders fetched successfully", response)
    );
}

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> pay(@PathVariable Long orderId,
                       @RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(orderId, request.getMethod());
        PaymentResponse response = new PaymentResponse();
        response.setOrderId(payment.getOrder().getId());
        response.setPaymentId(payment.getPaymentId());
        response.setStatus(payment.getStatus());
        response.setMethod(payment.getMethod());

        ApiResponse<PaymentResponse> body = ApiResponse.success("Payment processed successfully", response);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    private OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(String.valueOf(order.getId()));
        response.setAddress(order.getAddress());
        response.setLatitude(order.getLatitude());
        response.setLongitude(order.getLongitude());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount() == null ? 0 : order.getTotalAmount());

        List<OrderItemDTO> items = order.getItems().stream().map(this::toOrderItemDTO).collect(Collectors.toList());
        response.setItems(items);
        return response;
    }

    private OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setWeight(orderItem.getProductVariant().getWeight());
        dto.setProductName(orderItem.getProductVariant().getProduct().getProductName());
        return dto;
    }
}