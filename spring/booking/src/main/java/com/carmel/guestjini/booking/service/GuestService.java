package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.Guest;

import java.util.Date;
import java.util.Optional;

public interface GuestService {
    Guest save(Guest guest);
    Optional<Guest> findByBooking(Booking booking);

    void delete(Guest guestToDelete);

    Optional<Guest> findById(String guestId);

    Guest doCheckout(Guest guest, Date actualCheckout) throws Exception;
}
