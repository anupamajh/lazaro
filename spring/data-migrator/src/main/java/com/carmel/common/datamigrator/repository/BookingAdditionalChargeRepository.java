package com.carmel.common.datamigrator.repository;

import com.carmel.common.datamigrator.model.BookingAdditionalCharge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingAdditionalChargeRepository extends JpaRepository<BookingAdditionalCharge, String> {
}
