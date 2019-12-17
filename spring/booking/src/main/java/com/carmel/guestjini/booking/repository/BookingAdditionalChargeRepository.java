package com.carmel.guestjini.booking.repository;

import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingAdditionalChargeRepository extends JpaRepository<BookingAdditionalCharge, String> {

    List<BookingAdditionalCharge> findAllByPackageId(String packageId);
}
