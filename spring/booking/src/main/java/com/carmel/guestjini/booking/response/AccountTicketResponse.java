package com.carmel.guestjini.booking.response;

import com.carmel.guestjini.booking.model.DTO.AccountTicket;

import java.util.List;

public class AccountTicketResponse {
    private AccountTicket accountTicket;
    private List<AccountTicket> accountTicketList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AccountTicket getAccountTicket() {
        return accountTicket;
    }

    public void setAccountTicket(AccountTicket accountTicket) {
        this.accountTicket = accountTicket;
    }

    public List<AccountTicket> getAccountTicketList() {
        return accountTicketList;
    }

    public void setAccountTicketList(List<AccountTicket> accountTicketList) {
        this.accountTicketList = accountTicketList;
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
