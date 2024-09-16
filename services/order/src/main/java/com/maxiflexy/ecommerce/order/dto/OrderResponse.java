package com.maxiflexy.ecommerce.order.dto;

import com.maxiflexy.ecommerce.order.entity.enums.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String CustomerId
) {
}
