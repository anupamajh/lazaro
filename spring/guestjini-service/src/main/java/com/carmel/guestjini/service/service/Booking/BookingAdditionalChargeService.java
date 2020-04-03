package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.BookingAdditionalCharge;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;
import com.carmel.guestjini.service.response.Booking.BookingAdditionalChargeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookingAdditionalChargeService {
    BookingAdditionalCharge save(BookingAdditionalCharge bookingAdditionalCharge);

    List<BookingAdditionalCharge> findAllByPackageId(String packageId);

    void deleteAll(List<BookingAdditionalCharge> bookingAdditionalCharges);

    Optional<BookingAdditionalCharge> findById(String id);

    List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking);

    List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBookingAndBillingCycle(int isDeleted, String clientId, Booking booking, int billingCycle);

    Page<BookingAdditionalCharge> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable);

    Page<BookingAdditionalCharge> findAll(Specification<BookingAdditionalCharge> textInAllColumns, Pageable pageable);

    BookingAdditionalChargeResponse getRecurringPackageCharges(GuestDTO guest) throws Exception;

    BookingAdditionalChargeResponse getOneTimePackageCharges(GuestDTO guest) throws Exception;
}
