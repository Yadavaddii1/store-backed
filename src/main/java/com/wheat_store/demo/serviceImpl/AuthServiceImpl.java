package com.wheat_store.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wheat_store.demo.DTO.SignupRequest;
import com.wheat_store.demo.DTO.UserDTO;
import com.wheat_store.demo.ExpectionHandler.InvalidPasswordException;
import com.wheat_store.demo.ExpectionHandler.UserNotFoundException;
import com.wheat_store.demo.model.Customer;
import com.wheat_store.demo.model.Role;
import com.wheat_store.demo.repository.customerRepository;
import com.wheat_store.demo.security.JwtUtil;
import com.wheat_store.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private customerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String signup(SignupRequest request) {
        if (customerRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new UserNotFoundException("This username is already taken");
        }

        if (customerRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new UserNotFoundException("An account is already registered with this phone number");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new com.wheat_store.demo.ExpectionHandler.PasswordMismatchException ("The passwords entered do not match.");
        }
        //throw new com.wheat_store.demo.ExpectionHandler.PasswordMismatchException ("");
        

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setUserName(request.getUserName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        customer.setRole(Role.ADMIN);

        customerRepository.save(customer);

        return "Your account has been created successfully.";
    }

    @Override
    public String login(UserDTO request) {
        Customer customer = customerRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new UserNotFoundException("No account found with the provided details."));

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        
    return JwtUtil.generateToken(
        customer.getUserName(),
        customer.getRole().name());
    }

}
