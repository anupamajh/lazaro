package com.carmel.guestjini.payments.repository;

import com.carmel.guestjini.payments.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, String > {
}
