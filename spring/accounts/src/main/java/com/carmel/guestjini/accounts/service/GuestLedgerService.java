package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.DTO.TransactionData;

import java.util.List;

public interface GuestLedgerService {
    List<TransactionData> getGuestLedger(String guestId);
}
