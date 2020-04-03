package com.carmel.guestjini.service.repository.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYCDocs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KYCDocsRepository extends JpaRepository<KYCDocs, String> {

    List<KYCDocs> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    Page<KYCDocs> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);

    Page<KYCDocs> findAll(Specification<KYCDocs> textInAllColumns, Pageable pageable);
}
