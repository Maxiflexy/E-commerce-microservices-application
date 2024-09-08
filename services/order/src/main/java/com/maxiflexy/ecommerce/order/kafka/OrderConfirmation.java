package com.maxiflexy.ecommerce.order.kafka;

import com.maxiflexy.ecommerce.order.customer.CustomerResponse;
import com.maxiflexy.ecommerce.order.entity.enums.PaymentMethod;
import com.maxiflexy.ecommerce.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
