package com.wheat_store.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheat_store.demo.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

}



