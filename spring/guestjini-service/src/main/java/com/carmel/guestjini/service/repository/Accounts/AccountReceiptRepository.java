package com.carmel.guestjini.service.repository.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountReceiptRepository extends JpaRepository<AccountReceipts, String> {
    List<AccountReceipts> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId);

    Page<AccountReceipts> findAll(Specification<AccountReceipts> textInAllColumns, Pageable pageable);

    Page<AccountReceipts> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable);

    List<AccountReceipts> findAllByBookingIdAndReceiptStatusAndIsDeleted(String id, int receiptStatus, int isDeleted);
}
