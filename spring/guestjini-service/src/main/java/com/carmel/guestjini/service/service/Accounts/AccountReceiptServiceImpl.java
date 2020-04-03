package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.carmel.guestjini.service.repository.Accounts.AccountReceiptRepository;
import com.carmel.guestjini.service.response.Accounts.AccountReceiptsResponse;
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

    @Override
    public List<AccountReceipts> findAllByBookingIdAndReceiptStatusAndIsDeleted(String id, int receiptStatus, int isDeleted) {
        return accountReceiptRepository.findAllByBookingIdAndReceiptStatusAndIsDeleted(id, receiptStatus, isDeleted);
    }

    @Override
    public AccountReceiptsResponse saveAccountReceiept(AccountReceipts accountReceipts) throws Exception {
        try{
            AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
            accountReceipts = this.save(accountReceipts);
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setAccountReceipts(accountReceipts);
            return accountReceiptsResponse;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public AccountReceiptsResponse updateGuestReceipts(String bookingId, String guestId) throws Exception {
        try{
            AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            List<AccountReceipts> accountReceiptsList = this.findAllByIsDeletedAndBookingId(0, bookingId);
            for (AccountReceipts accountReceipts : accountReceiptsList) {
                accountReceipts.setGuestId(guestId);
                this.save(accountReceipts);
            }
            accountReceiptsResponse.setAccountReceiptsList(this.findAllByIsDeletedAndBookingId(0, bookingId));
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setError("");
            return accountReceiptsResponse;
        }catch (Exception ex){
            throw ex;
        }
    }
}
