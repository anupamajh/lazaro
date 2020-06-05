package com.carmel.common.datamigrator.Services;

import com.carmel.common.datamigrator.model.Booking;
import com.carmel.common.datamigrator.model.Guest;

import java.util.Date;

public interface BookingService {
    void doCheckIn(Booking booking, Date actualCheckInDate) throws Exception;
}
