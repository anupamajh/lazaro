package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking save(Booking booking);

    Optional<Booking> findById(String id);

    List<Booking> findAll(Specification<Booking> bookingSpecification);

    List<Booking> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Booking> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Booking> findAll(Specification<Booking> textInAllColumns, Pageable pageable);

    Guest doCheckIn(Booking booking, Date actualCheckInDate) throws Exception;

    Booking doCancelCheckIn(Booking booking)  throws Exception;
}
