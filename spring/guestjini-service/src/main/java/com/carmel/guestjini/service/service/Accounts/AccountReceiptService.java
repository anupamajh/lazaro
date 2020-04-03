package com.carmel.guestjini.service.service.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.carmel.guestjini.service.response.Accounts.AccountReceiptsResponse;
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

    List<AccountReceipts> findAllByBookingIdAndReceiptStatusAndIsDeleted(String id, int receiptStatus, int isDeleted);

    AccountReceiptsResponse saveAccountReceiept(AccountReceipts accountReceipts) throws Exception;

    AccountReceiptsResponse updateGuestReceipts(String bookingId, String guestId) throws Exception;
}
