package com.maxiflexy.ecommerce.order.payment;

import com.maxiflexy.ecommerce.order.customer.CustomerResponse;
import com.maxiflexy.ecommerce.order.entity.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
