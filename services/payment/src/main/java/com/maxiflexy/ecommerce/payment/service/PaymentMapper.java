package com.maxiflexy.ecommerce.payment.service;

import com.maxiflexy.ecommerce.payment.dto.PaymentRequest;
import com.maxiflexy.ecommerce.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .orderId(paymentRequest.orderId())
                .build();
    }
}
