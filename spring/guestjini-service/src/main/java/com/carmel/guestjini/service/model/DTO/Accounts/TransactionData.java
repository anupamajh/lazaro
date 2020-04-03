package com.carmel.guestjini.service.model.DTO.Accounts;

import java.util.Date;

public class TransactionData {
    private String transactionId;
    private Double runningBalance;
    private Double creditAmount;
    private Double debitAmount;
    private String transactionNumber;
    private String transactionNarration;
    private int transactionType;
    private Date transactionDate;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getRunningBalance() {
        return runningBalance;
    }

    public void setRunningBalance(Double runningBalance) {
        this.runningBalance = runningBalance;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public String getTransactionNarration() {
        return transactionNarration;
    }

    public void setTransactionNarration(String transactionNarration) {
        this.transactionNarration = transactionNarration;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
