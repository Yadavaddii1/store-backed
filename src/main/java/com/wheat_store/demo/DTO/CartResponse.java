package com.wheat_store.demo.DTO;

import java.util.List;
import java.util.UUID;

public class CartResponse {
    private UUID cartId;
    private List<CartItemResponse> items;
    private double totalAmount;

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
