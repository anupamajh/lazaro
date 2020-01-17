package com.carmel.guestjini.accounts.repository;

import com.carmel.guestjini.accounts.model.AccountTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTicketRepository extends JpaRepository<AccountTicket, String> {
    List<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId);

    Page<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable);

    Page<AccountTicket> findAll(Specification<AccountTicket> textInAllColumns, Pageable pageable);

    List<AccountTicket> findAllByGuestId(String guestId);
}