package com.carmel.common.dbservice.Base.AccountHead.Response;

import com.carmel.common.dbservice.Base.AccountHead.DTO.AccountHeadDTO;
import com.carmel.common.dbservice.Base.AccountHead.Model.AccountHead;

import java.util.ArrayList;
import java.util.List;

public class AccountHeadResponse {

    private AccountHeadDTO accountHead;
    private List<AccountHeadDTO> accountHeadList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AccountHeadDTO getAccountHead() {
        return accountHead;
    }

    public void setAccountHead(AccountHead accountHead) {
        this.accountHead = new AccountHeadDTO(accountHead);
    }

    public List<AccountHeadDTO> getAccountHeadList() {
        return accountHeadList;
    }

    public void setAccountHeadList(List<AccountHead> accountHeadList) {
        this.accountHeadList = new ArrayList<>();
        accountHeadList.forEach(accountHead1 -> {
            this.accountHeadList.add(new AccountHeadDTO(accountHead1));
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
