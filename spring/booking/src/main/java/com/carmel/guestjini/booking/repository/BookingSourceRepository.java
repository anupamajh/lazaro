package com.carmel.guestjini.booking.repository;

import com.carmel.guestjini.booking.model.BookingSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingSourceRepository extends JpaRepository<BookingSource, String> {
    List<BookingSource> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<BookingSource> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<BookingSource> findAll(Specification<BookingSource> textInAllColumns, Pageable pageable);

    List<BookingSource> findAllByClientIdAndTitleAndIsDeleted(String clientId, String title, int isDeleted);

    List<BookingSource> findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(String clientId, String title, String id, int isDeleted);
}
