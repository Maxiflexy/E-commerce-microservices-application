package com.maxiflexy.ecommerce.order.dto;

import com.maxiflexy.ecommerce.order.product.PurchaseRequest;
import com.maxiflexy.ecommerce.order.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should be precise")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be empty")
        @NotBlank(message = "Customer should not be blank")
        String customerId,

        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}
