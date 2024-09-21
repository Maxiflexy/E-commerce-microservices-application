package com.maxiflexy.ecommerce.payment.service;

import com.maxiflexy.ecommerce.payment.dto.PaymentRequest;

public interface PaymentService {

    Integer createPayment(PaymentRequest paymentRequest);
}
