package com.maxiflexy.ecommerce.order.service;

import com.maxiflexy.ecommerce.order.dto.OrderRequest;
import com.maxiflexy.ecommerce.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }
}
