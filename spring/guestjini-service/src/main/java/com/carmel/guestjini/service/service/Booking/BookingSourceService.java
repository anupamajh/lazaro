package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.BookingSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookingSourceService {
    BookingSource save(BookingSource bookingSource);

    Optional<BookingSource> findById(String id);

    List<BookingSource> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<BookingSource> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<BookingSource> findAll(Specification<BookingSource> textInAllColumns, Pageable pageable);

    List<BookingSource> findAllByClientIdAndTitle(String clientId, String title);

    List<BookingSource> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);
}
