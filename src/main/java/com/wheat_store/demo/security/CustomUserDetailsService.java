package com.wheat_store.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.wheat_store.demo.repository.customerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private customerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUserName(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
