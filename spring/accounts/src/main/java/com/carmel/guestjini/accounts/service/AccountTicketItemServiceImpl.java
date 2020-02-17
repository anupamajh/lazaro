package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicketItem;
import com.carmel.guestjini.accounts.repository.AccountTicketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTicketItemServiceImpl implements AccountTicketItemService {

    @Autowired
    AccountTicketItemRepository accountTicketItemRepository;

    @Override
    public AccountTicketItem save(AccountTicketItem accountTicketItem) {
        return accountTicketItemRepository.save(accountTicketItem);
    }

    @Override
    public List<AccountTicketItem> findAllByTicketId(String ticketId) {
        return accountTicketItemRepository.findAllByTicketId(ticketId);
    }
}
