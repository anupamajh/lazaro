package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.Guest;

import java.util.Optional;

public interface GuestService {
    Guest save(Guest guest);
    Optional<Guest> findByBooking(Booking booking);

    void delete(Guest guestToDelete);
}
