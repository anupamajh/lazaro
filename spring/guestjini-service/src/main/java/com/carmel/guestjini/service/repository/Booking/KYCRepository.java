package com.carmel.guestjini.service.repository.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KYCRepository extends JpaRepository<KYC, String> {
    List<KYC> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    Page<KYC> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);
}
