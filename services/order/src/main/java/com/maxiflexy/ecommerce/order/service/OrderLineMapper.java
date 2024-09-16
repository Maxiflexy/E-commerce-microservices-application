package com.maxiflexy.ecommerce.order.service;

import com.maxiflexy.ecommerce.order.dto.OrderLineRequest;
import com.maxiflexy.ecommerce.order.dto.OrderLineResponse;
import com.maxiflexy.ecommerce.order.entity.Order;
import com.maxiflexy.ecommerce.order.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.id())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
