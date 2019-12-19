package com.carmel.guestjini.accounts.response;

import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.AccountTicketDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountTicketResponse {
    private AccountTicketDTO accountTicket;
    private List<AccountTicketDTO> accountTicketList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AccountTicketDTO getAccountTicket() {
        return accountTicket;
    }

    public void setAccountTicket(AccountTicket accountTicket) {
        this.accountTicket = new AccountTicketDTO(accountTicket);
    }

    public List<AccountTicketDTO> getAccountTicketList() {
        return accountTicketList;
    }

    public void setAccountTicketList(List<AccountTicket> accountTicketList) {
        this.accountTicketList = new ArrayList<>();
        accountTicketList.forEach(accountTicket1 -> {
            this.accountTicketList.add(new AccountTicketDTO(accountTicket1));
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
