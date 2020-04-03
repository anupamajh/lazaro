package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.DTO.Accounts.AccountTicketDTO;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;
import com.carmel.guestjini.service.response.Accounts.AccountTicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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

    void generateDayInvoices(GuestDTO guest) throws  Exception;

    void generateMonthInvoices(String guestId, int month, int year) throws  Exception;

    List<AccountTicket> findAllByGuestIdAndTicketIdentifierAndTicketStatus(String id, int transactionIdentifier, int ticketStatus);

    AccountTicketResponse saveAccountTicket(AccountTicketDTO accountTicket) throws Exception;

    AccountTicketResponse deleteAccountTicketsByGuest(String id) throws Exception;

    void generateDayRentInvoice(Guest guest) throws Exception;

    void generateMonthRentInvoice(Guest guest) throws Exception;
}
