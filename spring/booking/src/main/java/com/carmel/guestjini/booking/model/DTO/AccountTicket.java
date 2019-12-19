package com.carmel.guestjini.booking.model.DTO;

import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AccountTicket {
    private String id;
    private String clientId;
    private String orgId;
    private String bookingId;
    private String guestId;
    private String accountHeadId;
    private int ticketIdentifier;
    private String ticketNumber;
    private String templateId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ticketDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date periodFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date periodUpto;
    private String ticketNarration;
    private Double itemTotal;
    private Double taxTotal;
    private Double discount;
    private Double netTotal;
    private int ticketStatus;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public AccountTicket() {
    }

    public AccountTicket(BookingAdditionalCharge bookingAdditionalCharge) {
        this.clientId = bookingAdditionalCharge.getClientId();
        this.orgId = bookingAdditionalCharge.getClientId();
        this.bookingId = bookingAdditionalCharge.getClientId();
        this.guestId = bookingAdditionalCharge.getClientId();
        this.accountHeadId = bookingAdditionalCharge.getClientId();
         this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.clientId = bookingAdditionalCharge.getClientId();
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

    public int getTicketIdentifier() {
        return ticketIdentifier;
    }

    public void setTicketIdentifier(int ticketIdentifier) {
        this.ticketIdentifier = ticketIdentifier;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodUpto() {
        return periodUpto;
    }

    public void setPeriodUpto(Date periodUpto) {
        this.periodUpto = periodUpto;
    }

    public String getTicketNarration() {
        return ticketNarration;
    }

    public void setTicketNarration(String ticketNarration) {
        this.ticketNarration = ticketNarration;
    }

    public Double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(int ticketStatus) {
        this.ticketStatus = ticketStatus;
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
