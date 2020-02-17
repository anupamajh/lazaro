package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountTicketItem;

import java.util.List;

public interface AccountTicketItemService {
    AccountTicketItem save(AccountTicketItem accountTicketItem);

    List<AccountTicketItem> findAllByTicketId(String ticketId);
}
