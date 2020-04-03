package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface KYCService {
    KYC save(KYC kyc);

    Optional<KYC> findById(String id);

    List<KYC> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    Page<KYC> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);

    Page<KYC> findAll(Specification<KYC> textInAllColumns, Pageable pageable);
}
