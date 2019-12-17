package com.carmel.guestjini.booking.repository;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.KYC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KYCRepository extends JpaRepository<KYC, String> {
    List<KYC> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    Page<KYC> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);
}
