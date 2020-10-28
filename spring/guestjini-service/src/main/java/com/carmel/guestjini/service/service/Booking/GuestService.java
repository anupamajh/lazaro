package com.carmel.guestjini.service.service.Booking;


import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.response.Booking.GuestResponse;

import java.util.Date;
import java.util.Optional;

public interface GuestService {
    Guest save(Guest guest);
    Optional<Guest> findByBooking(Booking booking);

    void delete(Guest guestToDelete);

    Optional<Guest> findById(String guestId);

    Guest doCheckout(Guest guest, Date actualCheckout) throws Exception;

    Optional<Guest> findByEmail(String email);

    Optional<Guest> findByPhone(String phone);

    GuestResponse getGuestById(String guestId) throws Exception;

    GuestResponse getGuestByEmail(String userName) throws Exception;

    GuestResponse getGuestWithinPeriod(int month, int year) throws Exception;
}
