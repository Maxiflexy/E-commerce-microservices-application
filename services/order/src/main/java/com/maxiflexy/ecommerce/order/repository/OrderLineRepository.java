package com.maxiflexy.ecommerce.order.repository;

import com.maxiflexy.ecommerce.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
