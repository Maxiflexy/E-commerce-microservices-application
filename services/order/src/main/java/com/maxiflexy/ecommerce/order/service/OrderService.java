package com.maxiflexy.ecommerce.order.service;

import com.maxiflexy.ecommerce.order.dto.OrderRequest;
import com.maxiflexy.ecommerce.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer orderId);
}
