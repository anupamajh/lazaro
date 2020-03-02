package com.carmel.guestjini.booking.repository;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, String> {

    Optional<Guest> findByBooking(Booking booking);

    Optional<Guest> finByEmail(String email);
}
