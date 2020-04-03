package com.carmel.guestjini.service.response.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.carmel.guestjini.service.model.DTO.Accounts.AccountReceiptsDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountReceiptsResponse {
    private AccountReceiptsDTO accountReceipts;
    private List<AccountReceiptsDTO> accountReceiptsList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AccountReceiptsDTO getAccountReceipts() {
        return accountReceipts;
    }

    public void setAccountReceipts(AccountReceipts accountReceipts) {
        this.accountReceipts = new AccountReceiptsDTO(accountReceipts);
    }

    public List<AccountReceiptsDTO> getAccountReceiptsList() {
        return accountReceiptsList;
    }

    public void setAccountReceiptsList(List<AccountReceipts> accountReceiptsList) {
        this.accountReceiptsList = new ArrayList<>();
        accountReceiptsList.forEach(accountReceipts1 -> {
            this.accountReceiptsList.add(new AccountReceiptsDTO(accountReceipts1));
        });
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
