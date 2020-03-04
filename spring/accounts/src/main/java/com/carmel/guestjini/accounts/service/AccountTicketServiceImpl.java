package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.repository.AccountTicketRepository;
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
    public void generateDayInvoices(Guest guest) throws Exception {
        try {
            dayRentService.generateInvoices(guest);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void generateMonthInvoices(String guestId, int month, int year) throws Exception {
        try {
            monthRentService.generateInvoices(month,year,guestId);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<AccountTicket> findAllByGuestIdAndTicketIdentifierAndTicketStatus(String id, int transactionIdentifier, int ticketStatus) {
        return accountTicketRepository.findAllByGuestIdAndTicketIdentifierAndTicketStatus(id, transactionIdentifier, ticketStatus);
    }
}
