package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(String id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findAll(Specification<Booking> bookingSpecification) {
        return bookingRepository.findAll(bookingSpecification);
    }
}
