package com.wheat_store.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("Aditya@123"));
        //$2a$10$ayq27o1kzlGvLtPWiOZfme35IIqZephfLhJd7cllVtMcA2yj9rlIu
    }
}