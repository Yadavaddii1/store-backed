package com.wheat_store.demo.controller;

import com.wheat_store.demo.model.Cart;
import com.wheat_store.demo.model.CartItem;
import com.wheat_store.demo.service.CartService;

import org.springframework.web.bind.annotation.*;

import com.wheat_store.demo.DTO.ApiResponse;
import com.wheat_store.demo.DTO.CartItemResponse;
import com.wheat_store.demo.DTO.CartResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CartResponse>> getCart() {
        Cart cart = cartService.getCart();
        ApiResponse<CartResponse> body =
                ApiResponse.success("Cart fetched successfully", toCartResponse(cart));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartResponse>> addToCart(@RequestBody Map<String, Object> request) {
        String variantId = request.get("variantId").toString();
        Integer quantity = Integer.parseInt(request.get("quantity").toString());
        Cart cart = cartService.addToCart(variantId, quantity);
        ApiResponse<CartResponse> body =
                ApiResponse.success("Item added to cart successfully", toCartResponse(cart));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ApiResponse<CartResponse>> removeItem(@PathVariable Long id) {
        Cart cart = cartService.removeItem(id);
        ApiResponse<CartResponse> body =
                ApiResponse.success("Item removed from cart successfully", toCartResponse(cart));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<CartResponse>> clearCart() {
        Cart cart = cartService.clearCart();
        ApiResponse<CartResponse> body =
                ApiResponse.success("Cart cleared successfully", toCartResponse(cart));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    private CartResponse toCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setCartId(cart.getId());
        List<CartItemResponse> items = cart.getItems().stream()
                .map(this::toCartItemResponse)
                .collect(Collectors.toList());
        response.setItems(items);
        response.setTotalAmount(items.stream().mapToDouble(CartItemResponse::getLineTotal).sum());
        return response;
    }

    private CartItemResponse toCartItemResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setCartItemId(item.getId());
        response.setVariantId(item.getVariant().getId().toString());
        response.setWeight(item.getVariant().getWeight());
        response.setUnitPrice(item.getVariant().getPrice());
        response.setQuantity(item.getQuantity());
        response.setLineTotal(item.getQuantity() * item.getVariant().getPrice());
        response.setProductName(item.getVariant().getProduct().getProductName());
        return response;
    }
}