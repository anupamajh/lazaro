package com.carmel.guestjini.service.service.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountTicketItem;

import java.util.List;

public interface AccountTicketItemService {
    AccountTicketItem save(AccountTicketItem accountTicketItem);

    List<AccountTicketItem> findAllByTicketId(String ticketId);
}
