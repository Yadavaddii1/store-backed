package com.wheat_store.demo.service;

import com.wheat_store.demo.model.*;
import com.wheat_store.demo.repository.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final customerRepository customerRepository;
    private final ProductVariantRepository variantRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       customerRepository customerRepository,
                       ProductVariantRepository variantRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.variantRepository = variantRepository;
    }

    private Customer getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return customerRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Cart getCart() {
        Customer user = getCurrentUser();

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public Cart addToCart(String variantId, Integer quantity) {

        Customer user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        ProductVariant variant = variantRepository.findById(java.util.UUID.fromString(variantId))
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        CartItem item = new CartItem();
        item.setVariant(variant);
        item.setQuantity(quantity);
        item.setCart(cart);

        cart.getItems().add(item);

        return cartRepository.save(cart);
    }

    public Cart removeItem(Long cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(item);

        return getCart();
    }

    public Cart clearCart() {
        Cart cart = getCart();
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}