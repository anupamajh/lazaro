package com.carmel.guestjini.service.repository.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findAll(Specification<Booking> bookingSpecification);

    List<Booking> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Booking> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Booking> findAll(Specification<Booking> textInAllColumns, Pageable pageable);

    Optional<Booking> findByPhoneAndBookingStatus(String mobileNumber, int BookingStatus);

    List<Booking> findAllByIsDeletedAndClientIdAndPhone(int isDeleted, String clientId, String phone);
}
