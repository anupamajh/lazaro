package com.carmel.guestjini.service.service.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountTicket;

import java.util.List;

public interface MonthRentService {
    List<AccountTicket> generateInvoices(int month, int year, String guestId)  throws Exception;
}
