package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.Guest;

import java.util.List;

public interface DayRentService {
    void generateInvoices(Guest guest)  throws Exception;

    List<AccountTicket> getGuestTickets(Guest guest, int ticketIdentifier);
}
