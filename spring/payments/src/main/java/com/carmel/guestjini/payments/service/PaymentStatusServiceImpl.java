package com.carmel.guestjini.payments.service;

import com.carmel.guestjini.payments.model.PaymentStatus;
import com.carmel.guestjini.payments.repository.PaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

    @Autowired
    PaymentStatusRepository paymentStatusRepository;

    @Override
    public PaymentStatus save(PaymentStatus paymentStatus) {
        return paymentStatusRepository.save(paymentStatus);
    }

    @Override
    public Optional<PaymentStatus> findById(String orderId) {
        return paymentStatusRepository.findById(orderId);
    }
}
