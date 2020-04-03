package com.carmel.guestjini.service.model.DTO.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AccountReceiptsDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String bookingId;
    private String guestId;
    private String accountHeadId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiptDate;
    private String receiptNarration;
    private Double amount;
    private int instrument;
    private String instrumentNarration;
    private int receiptStatus;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public AccountReceiptsDTO() {
    }

    public AccountReceiptsDTO(AccountReceipts accountReceipts) {
        this.id = accountReceipts.getId();
        this.clientId = accountReceipts.getClientId();
        this.orgId = accountReceipts.getOrgId();
        this.bookingId = accountReceipts.getBookingId();
        this.guestId = accountReceipts.getGuestId();
        this.accountHeadId = accountReceipts.getAccountHeadId();
        this.receiptDate = accountReceipts.getReceiptDate();
        this.receiptNarration = accountReceipts.getReceiptNarration();
        this.amount = accountReceipts.getAmount();
        this.instrument = accountReceipts.getInstrument();
        this.instrumentNarration = accountReceipts.getInstrumentNarration();
        this.receiptStatus = accountReceipts.getReceiptStatus();
        this.createdBy = accountReceipts.getCreatedBy();
        this.creationTime = accountReceipts.getCreationTime();
        this.lastModifiedBy = accountReceipts.getLastModifiedBy();
        this.lastModifiedTime = accountReceipts.getLastModifiedTime();
        this.isDeleted = accountReceipts.getIsDeleted();
        this.deletedBy = accountReceipts.getDeletedBy();
        this.deletedTime = accountReceipts.getDeletedTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getAccountHeadId() {
        return accountHeadId;
    }

    public void setAccountHeadId(String accountHeadId) {
        this.accountHeadId = accountHeadId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptNarration() {
        return receiptNarration;
    }

    public void setReceiptNarration(String receiptNarration) {
        this.receiptNarration = receiptNarration;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getInstrument() {
        return instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public String getInstrumentNarration() {
        return instrumentNarration;
    }

    public void setInstrumentNarration(String instrumentNarration) {
        this.instrumentNarration = instrumentNarration;
    }

    public int getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(int receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }
}
