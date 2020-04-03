package com.carmel.guestjini.service.model.DTO.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountTicketItem;

import java.util.Date;

public class AccountTicketItemDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String bookingId;
    private String guestId;
    private String ticketId;
    private int lineNo;
    private String itemNarration;
    private Double rate;
    private  Double qty;
    private String qtyUnit;
    private Double subTotal;
    private Double taxValue;
    private  Integer taxValueIdentifier;
    private Double itemTotal;
    private int hasVoucher;
    private String voucherId;
    private Double amountUsed;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public AccountTicketItemDTO() {
    }

    public AccountTicketItemDTO(AccountTicketItem accountTicketItem) {
        this.id = accountTicketItem.getId();
        this.clientId = accountTicketItem.getClientId();
        this.orgId = accountTicketItem.getOrgId();
        this.bookingId = accountTicketItem.getBookingId();
        this.guestId = accountTicketItem.getGuestId();
        this.ticketId = accountTicketItem.getTicketId();
        this.lineNo = accountTicketItem.getLineNo();
        this.itemNarration = accountTicketItem.getItemNarration();
        this.rate = accountTicketItem.getRate();
        this.qty = accountTicketItem.getQty();
        this.qtyUnit = accountTicketItem.getQtyUnit();
        this.subTotal = accountTicketItem.getSubTotal();
        this.taxValue = accountTicketItem.getTaxValue();
        this.taxValueIdentifier = accountTicketItem.getTaxValueIdentifier() == null ? 0 : accountTicketItem.getTaxValueIdentifier();
        this.itemTotal = accountTicketItem.getItemTotal();
        this.hasVoucher = accountTicketItem.getHasVoucher();
        this.voucherId = accountTicketItem.getVoucherId();
        this.amountUsed = accountTicketItem.getAmountUsed();
        this.createdBy = accountTicketItem.getCreatedBy();
        this.creationTime = accountTicketItem.getCreationTime();
        this.lastModifiedBy = accountTicketItem.getLastModifiedBy();
        this.lastModifiedTime = accountTicketItem.getLastModifiedTime();
        this.isDeleted = accountTicketItem.getIsDeleted();
        this.deletedBy = accountTicketItem.getDeletedBy();
        this.deletedTime = accountTicketItem.getDeletedTime();
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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getItemNarration() {
        return itemNarration;
    }

    public void setItemNarration(String itemNarration) {
        this.itemNarration = itemNarration;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public int getTaxValueIdentifier() {
        return taxValueIdentifier;
    }

    public void setTaxValueIdentifier(int taxValueIdentifier) {
        this.taxValueIdentifier = taxValueIdentifier;
    }

    public Double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public int getHasVoucher() {
        return hasVoucher;
    }

    public void setHasVoucher(int hasVoucher) {
        this.hasVoucher = hasVoucher;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public Double getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Double amountUsed) {
        this.amountUsed = amountUsed;
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
