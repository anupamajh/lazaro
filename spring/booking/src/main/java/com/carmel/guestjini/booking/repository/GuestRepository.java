package com.carmel.guestjini.booking.repository;

import com.carmel.guestjini.booking.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository  extends JpaRepository<Guest, String> {
}
