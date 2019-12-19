package com.carmel.guestjini.booking.repository;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingAdditionalChargeRepository extends JpaRepository<BookingAdditionalCharge, String> {

    List<BookingAdditionalCharge> findAllByPackageId(String packageId);

    List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    Page<BookingAdditionalCharge> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);

    Page<BookingAdditionalCharge> findAll(Specification<BookingAdditionalCharge> textInAllColumns, Pageable pageable);

    List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(int isDeleted, String clientId, Booking booking, int billingCycle);
}
