package com.carmel.guestjini.service.response.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.DTO.Accounts.AccountTicketDTO;
import com.carmel.guestjini.service.model.DTO.Accounts.TransactionData;

import java.util.ArrayList;
import java.util.List;

public class AccountTicketResponse {
    private AccountTicketDTO accountTicket;
    private List<AccountTicketDTO> accountTicketList;
    private List<TransactionData> ledger;
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

    public void setAccountTicketDTO(AccountTicketDTO accountTicketDTO) {
        this.accountTicket = (accountTicketDTO);
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

    public List<TransactionData> getLedger() {
        return ledger;
    }

    public void setLedger(List<TransactionData> ledger) {
        this.ledger = ledger;
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

    public void setAccountTicketListDTO(List<AccountTicketDTO> accountTicketList) {
        this.accountTicketList = accountTicketList;
    }

}
