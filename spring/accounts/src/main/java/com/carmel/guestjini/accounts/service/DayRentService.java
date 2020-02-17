package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.DTO.Guest;

public interface DayRentService {
    void generateInvoices(Guest guest)  throws Exception;
}
