package com.carmel.guestjini.booking.response;

import com.carmel.guestjini.booking.model.DTO.AccountReceipts;

import java.util.List;

public class AccountReceiptsResponse {
    private AccountReceipts accountReceipts;
    private List<AccountReceipts> accountReceiptsList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AccountReceipts getAccountReceipts() {
        return accountReceipts;
    }

    public void setAccountReceipts(AccountReceipts accountReceipts) {
        this.accountReceipts = accountReceipts;
    }

    public List<AccountReceipts> getAccountReceiptsList() {
        return accountReceiptsList;
    }

    public void setAccountReceiptsList(List<AccountReceipts> accountReceiptsList) {
        this.accountReceiptsList = accountReceiptsList;
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
