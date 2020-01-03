package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountTicketService {
    AccountTicket save(AccountTicket accountTicket);

    Optional<AccountTicket> findById(String id);

    List<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId);

    Page<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable);

    Page<AccountTicket> findAll(Specification<AccountTicket> textInAllColumns, Pageable pageable);

    List<AccountTicket> findAllByGuestId(String guestId);

    void deleteAll(List<AccountTicket> accountTickets);

    List<AccountTicket> generateDayInvoices(Guest guest) throws  Exception;

    List<AccountTicket> generateMonthInvoices(Guest guest) throws  Exception;
}
