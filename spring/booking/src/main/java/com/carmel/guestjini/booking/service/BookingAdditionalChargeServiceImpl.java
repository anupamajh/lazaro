package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.carmel.guestjini.booking.repository.BookingAdditionalChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
