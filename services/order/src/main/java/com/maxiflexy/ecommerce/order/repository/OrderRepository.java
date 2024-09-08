package com.maxiflexy.ecommerce.order.repository;

import com.maxiflexy.ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
