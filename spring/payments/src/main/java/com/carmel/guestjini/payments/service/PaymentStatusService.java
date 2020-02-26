package com.carmel.guestjini.payments.service;

import com.carmel.guestjini.payments.model.PaymentStatus;

import java.util.Optional;

public interface PaymentStatusService {
    PaymentStatus save(PaymentStatus paymentStatus);

    Optional<PaymentStatus> findById(String orderId);
}
