package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYCDocs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface KYCDocsService {
    KYCDocs save(KYCDocs kycDocs);

    Optional<KYCDocs> findById(String id);

    List<KYCDocs> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    Page<KYCDocs> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);

    Page<KYCDocs> findAll(Specification<KYCDocs> textInAllColumns, Pageable pageable);
}
