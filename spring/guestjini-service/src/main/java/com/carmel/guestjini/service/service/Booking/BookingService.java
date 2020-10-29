package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.Booking.BookingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

    Optional<Booking> findByPhoneAndBookingStatus(String mobileNumber, int bookingStatus);

    BookingResponse checkPhoneNumber(Map<String, String> formData, UserInfo userInfo) throws Exception;
}
