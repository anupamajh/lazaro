package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.DTO.Accounts.AccountTicketDTO;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;
import com.carmel.guestjini.service.repository.Accounts.AccountTicketRepository;
import com.carmel.guestjini.service.response.Accounts.AccountTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTicketServiceImpl implements AccountTicketService {

    @Autowired
    AccountTicketRepository accountTicketRepository;

    @Autowired
    DayRentService dayRentService;

    @Autowired
    MonthRentService monthRentService;

    @Override
    public AccountTicket save(AccountTicket accountTicket) {
        return accountTicketRepository.save(accountTicket);
    }

    @Override
    public Optional<AccountTicket> findById(String id) {
        return accountTicketRepository.findById(id);
    }

    @Override
    public List<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId) {
        return accountTicketRepository.findAllByIsDeletedAndBookingId(isDeleted, bookingId);
    }

    @Override
    public Page<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable) {
        return accountTicketRepository.findAllByIsDeletedAndBookingId(isDeleted, bookingId, pageable);
    }

    @Override
    public Page<AccountTicket> findAll(Specification<AccountTicket> textInAllColumns, Pageable pageable) {
        return accountTicketRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<AccountTicket> findAllByGuestId(String guestId) {
        return accountTicketRepository.findAllByGuestId(guestId);
    }

    @Override
    public void deleteAll(List<AccountTicket> accountTickets) {
        accountTicketRepository.deleteAll(accountTickets);
    }

    @Override
    public void generateDayInvoices(GuestDTO guest) throws Exception {
        try {
            dayRentService.generateInvoices(guest);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void generateMonthInvoices(String guestId, int month, int year) throws Exception {
        try {
            monthRentService.generateInvoices(month, year, guestId);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<AccountTicket> findAllByGuestIdAndTicketIdentifierAndTicketStatus(String id, int transactionIdentifier, int ticketStatus) {
        return accountTicketRepository.findAllByGuestIdAndTicketIdentifierAndTicketStatus(id, transactionIdentifier, ticketStatus);
    }

    @Override
    public AccountTicketResponse saveAccountTicket(AccountTicketDTO accountTicket) throws Exception {
        try {
            AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
            AccountTicket savedAccountTicket = this.save(accountTicket.getAccountTicket());
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setAccountTicket(savedAccountTicket);
            return accountTicketResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public AccountTicketResponse deleteAccountTicketsByGuest(String guestId) throws Exception {
        try {
            AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
            List<AccountTicket> accountTickets = this.findAllByGuestId(guestId);
            this.deleteAll(accountTickets);
            accountTicketResponse.setSuccess(true);
            return accountTicketResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void generateDayRentInvoice(Guest guest) throws Exception {
        try {
            dayRentService.generateInvoices(new GuestDTO(guest));
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void generateMonthRentInvoice(Guest guest) throws Exception {
        try {
            monthRentService.generateInvoices(0, 0, guest.getId());
        } catch (Exception ex) {
            throw ex;
        }
    }
}
