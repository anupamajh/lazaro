package com.carmel.guestjini.Models.Accounts;

import java.util.List;

public class AccountTicketResponse {
    private AccountTicket accountTicket;
    private List<AccountTicket> accountTicketList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private Boolean success;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(int currentRecords) {
        this.currentRecords = currentRecords;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
