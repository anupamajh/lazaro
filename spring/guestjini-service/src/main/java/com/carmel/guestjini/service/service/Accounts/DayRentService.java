package com.carmel.guestjini.service.service.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;

import java.util.List;

public interface DayRentService {
    void generateInvoices(GuestDTO guest)  throws Exception;

    List<AccountTicket> getGuestTickets(GuestDTO guest, int ticketIdentifier);
}
