package com.wheat_store.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.wheat_store.demo.DTO.SignupRequest;
import com.wheat_store.demo.DTO.UserDTO;
import com.wheat_store.demo.DTO.ApiResponse;
import com.wheat_store.demo.service.AuthService;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody SignupRequest request) {
        String result = authService.signup(request);
        ApiResponse<String> body = ApiResponse.success("User signed up successfully", result);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserDTO request) {
        String token = authService.login(request);
        ApiResponse<String> body = ApiResponse.success("Login successful", token);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
