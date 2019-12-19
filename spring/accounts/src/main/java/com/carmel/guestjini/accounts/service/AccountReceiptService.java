package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountReceipts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AccountReceiptService {
    AccountReceipts save(AccountReceipts accountReceipts);

    Optional<AccountReceipts> findById(String id);

    List<AccountReceipts> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId);

    Page<AccountReceipts> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable);

    Page<AccountReceipts> findAll(Specification<AccountReceipts> textInAllColumns, Pageable pageable);
}
