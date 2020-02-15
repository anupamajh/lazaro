package com.carmel.guestjini.accounts.repository;

import com.carmel.guestjini.accounts.model.AccountTicketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTicketItemRepository extends JpaRepository<AccountTicketItem, String > {
}
