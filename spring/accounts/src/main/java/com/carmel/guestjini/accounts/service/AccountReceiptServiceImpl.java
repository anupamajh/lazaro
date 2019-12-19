package com.carmel.guestjini.accounts.service;

import com.carmel.guestjini.accounts.model.AccountReceipts;
import com.carmel.guestjini.accounts.repository.AccountReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountReceiptServiceImpl implements AccountReceiptService{
    @Autowired
    AccountReceiptRepository accountReceiptRepository;

    @Override
    public AccountReceipts save(AccountReceipts accountReceipts) {
        return accountReceiptRepository.save(accountReceipts);
    }

    @Override
    public Optional<AccountReceipts> findById(String id) {
        return accountReceiptRepository.findById(id);
    }

    @Override
    public List<AccountReceipts> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId) {
        return accountReceiptRepository.findAllByIsDeletedAndBookingId(isDeleted, bookingId);
    }

    @Override
    public Page<AccountReceipts> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable) {
        return accountReceiptRepository.findAllByIsDeletedAndBookingId(isDeleted, bookingId, pageable);
    }

    @Override
    public Page<AccountReceipts> findAll(Specification<AccountReceipts> textInAllColumns, Pageable pageable) {
        return accountReceiptRepository.findAll(textInAllColumns, pageable);
    }
}
