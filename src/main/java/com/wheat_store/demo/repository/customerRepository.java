package com.wheat_store.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wheat_store.demo.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface customerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
