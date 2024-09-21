package com.maxiflexy.ecommerce.payment.repository;

import com.maxiflexy.ecommerce.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
