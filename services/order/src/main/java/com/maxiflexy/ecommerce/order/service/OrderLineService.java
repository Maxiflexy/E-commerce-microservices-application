package com.maxiflexy.ecommerce.order.service;

import com.maxiflexy.ecommerce.order.dto.OrderLineRequest;
import com.maxiflexy.ecommerce.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
