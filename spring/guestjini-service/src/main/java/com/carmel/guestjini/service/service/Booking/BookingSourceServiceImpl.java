package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.BookingSource;
import com.carmel.guestjini.service.repository.Booking.BookingSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingSourceServiceImpl implements BookingSourceService {

    @Autowired
    BookingSourceRepository bookingSourceRepository;

    @Override
    public BookingSource save(BookingSource bookingSource) {
        return bookingSourceRepository.save(bookingSource);
    }

    @Override
    public Optional<BookingSource> findById(String id) {
        return bookingSourceRepository.findById(id);
    }

    @Override
    public List<BookingSource> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return bookingSourceRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<BookingSource> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return bookingSourceRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<BookingSource> findAll(Specification<BookingSource> textInAllColumns, Pageable pageable) {
        return bookingSourceRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<BookingSource> findAllByClientIdAndTitle(String clientId, String title) {
        return bookingSourceRepository.findAllByClientIdAndTitleAndIsDeleted(clientId, title, 0);
    }

    @Override
    public List<BookingSource> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return bookingSourceRepository.findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(clientId, title, id, 0);
    }
}
