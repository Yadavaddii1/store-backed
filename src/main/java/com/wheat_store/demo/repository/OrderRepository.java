package com.wheat_store.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheat_store.demo.model.Customer;
import com.wheat_store.demo.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(Customer user);
}