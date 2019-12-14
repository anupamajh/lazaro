package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking save(Booking booking);

    Optional<Booking> findById(String id);

    List<Booking> findAll(Specification<Booking> bookingSpecification);
}
