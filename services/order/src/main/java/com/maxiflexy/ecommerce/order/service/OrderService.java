package com.maxiflexy.ecommerce.order.service;

import com.maxiflexy.ecommerce.order.dto.OrderRequest;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);
}
