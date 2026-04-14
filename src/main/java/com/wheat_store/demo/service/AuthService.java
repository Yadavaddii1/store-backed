package com.wheat_store.demo.service;

import com.wheat_store.demo.DTO.SignupRequest;
import com.wheat_store.demo.DTO.UserDTO;

public interface AuthService {

    String login(UserDTO request);
    String signup(SignupRequest request);
}
