package com.maxiflexy.ecommerce.payment.service.impl;

import com.maxiflexy.ecommerce.payment.dto.PaymentNotificationRequest;
import com.maxiflexy.ecommerce.payment.dto.PaymentRequest;
import com.maxiflexy.ecommerce.payment.notification.NotificationProducer;
import com.maxiflexy.ecommerce.payment.repository.PaymentRepository;
import com.maxiflexy.ecommerce.payment.service.PaymentMapper;
import com.maxiflexy.ecommerce.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(mapper.toPayment(paymentRequest));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email()
                )
        );
        return payment.getId();
    }
}
