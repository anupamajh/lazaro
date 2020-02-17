package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.Guest;

import java.util.List;

public interface MonthRentService {
    List<AccountTicket> generateInvoices(int month, int year, String guestId)  throws Exception;
}
