package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.AccountHead;

import java.util.List;

public class AccountHeadResponse {

    private AccountHead accountHead;
    private List<AccountHead> accountHeadList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AccountHead getAccountHead() {
        return accountHead;
    }

    public void setAccountHead(AccountHead accountHead) {
        this.accountHead = accountHead;
    }

    public List<AccountHead> getAccountHeadList() {
        return accountHeadList;
    }

    public void setAccountHeadList(List<AccountHead> accountHeadList) {
        this.accountHeadList = accountHeadList;
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
