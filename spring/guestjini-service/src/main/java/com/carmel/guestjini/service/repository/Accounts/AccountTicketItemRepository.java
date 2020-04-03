package com.carmel.guestjini.service.repository.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountTicketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTicketItemRepository extends JpaRepository<AccountTicketItem, String > {
    List<AccountTicketItem> findAllByTicketId(String ticketId);
}
