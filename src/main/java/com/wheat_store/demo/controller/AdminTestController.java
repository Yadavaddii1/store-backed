package com.wheat_store.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.wheat_store.demo.DTO.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> testAdmin() {
        ApiResponse<String> body =
                ApiResponse.success("Admin access granted", "Admin access granted");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
