package com.carmel.guestjini.service.service.Accounts;


import com.carmel.guestjini.service.model.DTO.Accounts.TransactionData;

import java.util.List;

public interface GuestLedgerService {
    List<TransactionData> getGuestLedger(String guestId);
}
