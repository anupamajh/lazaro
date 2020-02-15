package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.repository.AccountTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthRentServiceImpl implements MonthRentService{
    @Autowired
    AccountTicketRepository accountTicketRepository;

    @Override
    public List<AccountTicket> generateInvoices(Guest guest)   throws Exception{

        return null;
    }
}
