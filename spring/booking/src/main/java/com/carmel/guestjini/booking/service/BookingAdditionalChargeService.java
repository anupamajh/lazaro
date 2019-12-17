package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.BookingAdditionalCharge;

import java.util.List;

public interface BookingAdditionalChargeService {
    BookingAdditionalCharge save(BookingAdditionalCharge bookingAdditionalCharge);

    List<BookingAdditionalCharge> findAllByPackageId(String packageId);

    void deleteAll(List<BookingAdditionalCharge> bookingAdditionalCharges);
}
