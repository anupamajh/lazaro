package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountTicketItem;
import com.carmel.guestjini.service.repository.Accounts.AccountTicketItemRepository;
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
