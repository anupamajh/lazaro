package com.carmel.guestjini.service.repository.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, String> {

    Optional<Guest> findByBooking(Booking booking);

    Optional<Guest> findByEmail(String email);

    Optional<Guest> findByPhone(String phone);
}
