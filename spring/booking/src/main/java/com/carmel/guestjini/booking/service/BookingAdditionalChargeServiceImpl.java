package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.carmel.guestjini.booking.repository.BookingAdditionalChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingAdditionalChargeServiceImpl implements BookingAdditionalChargeService{

    @Autowired
    BookingAdditionalChargeRepository bookingAdditionalChargeRepository;

    @Override
    public BookingAdditionalCharge save(BookingAdditionalCharge bookingAdditionalCharge) {
        return bookingAdditionalChargeRepository.save(bookingAdditionalCharge);
    }

    @Override
    public List<BookingAdditionalCharge> findAllByPackageId(String packageId) {
        return bookingAdditionalChargeRepository.findAllByPackageId(packageId);
    }

    @Override
    public void deleteAll(List<BookingAdditionalCharge> bookingAdditionalCharges) {
        bookingAdditionalChargeRepository.deleteAll(bookingAdditionalCharges);
    }

    @Override
    public Optional<BookingAdditionalCharge> findById(String id) {
        return bookingAdditionalChargeRepository.findById(id);
    }

    @Override
    public List<BookingAdditionalCharge> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking) {
        return bookingAdditionalChargeRepository.findAllByIsDeletedAndClientIdAndBooking(isDeleted, clientId, booking);
    }

    @Override
    public Page<BookingAdditionalCharge> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable) {
        return bookingAdditionalChargeRepository.findAllByClientIdAndIsDeletedAndBooking(clientId, isDeleted,booking, pageable);
    }

    @Override
    public Page<BookingAdditionalCharge> findAll(Specification<BookingAdditionalCharge> textInAllColumns, Pageable pageable) {
        return bookingAdditionalChargeRepository.findAll(textInAllColumns, pageable);
    }
}
