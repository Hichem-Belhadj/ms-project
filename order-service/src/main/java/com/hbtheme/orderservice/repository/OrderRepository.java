package com.hbtheme.orderservice.repository;

import com.hbtheme.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}