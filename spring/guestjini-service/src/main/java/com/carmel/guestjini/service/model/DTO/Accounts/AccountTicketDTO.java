package com.carmel.guestjini.service.model.DTO.Accounts;


import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import com.carmel.guestjini.service.model.Accounts.AccountTicketItem;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTicketDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String bookingId;
    private String guestId;
    private String parentId;
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
    private List<AccountTicketItemDTO> accountTicketItems;

    public AccountTicketDTO() {
    }

    public AccountTicketDTO(AccountTicket accountTicket) {
        this.id = accountTicket.getId();
        this.clientId = accountTicket.getClientId();
        this.orgId = accountTicket.getOrgId();
        this.bookingId = accountTicket.getBookingId();
        this.guestId = accountTicket.getGuestId();
        this.accountHeadId = accountTicket.getAccountHeadId();
        this.ticketIdentifier = accountTicket.getTicketIdentifier();
        this.ticketNumber = accountTicket.getTicketNumber();
        this.templateId = accountTicket.getTemplateId();
        this.ticketDate = accountTicket.getTicketDate();
        this.periodFrom = accountTicket.getPeriodFrom();
        this.periodUpto = accountTicket.getPeriodUpto();
        this.ticketNarration = accountTicket.getTicketNarration();
        this.itemTotal = accountTicket.getItemTotal();
        this.taxTotal = accountTicket.getTaxTotal();
        this.discount = accountTicket.getDiscount();
        this.netTotal = accountTicket.getNetTotal();
        this.ticketStatus = accountTicket.getTicketStatus();
        this.createdBy = accountTicket.getCreatedBy();
        this.creationTime = accountTicket.getCreationTime();
        this.lastModifiedBy = accountTicket.getLastModifiedBy();
        this.lastModifiedTime = accountTicket.getLastModifiedTime();
        this.isDeleted = accountTicket.getIsDeleted();
        this.deletedBy = accountTicket.getDeletedBy();
        this.deletedTime = accountTicket.getDeletedTime();
        this.parentId = accountTicket.getParentId();
    }

    public AccountTicket getAccountTicket() {
        AccountTicket accountTicket = new AccountTicket();
        accountTicket.setId(accountTicket.getId());
        accountTicket.setClientId(accountTicket.getClientId());
        accountTicket.setOrgId(accountTicket.getOrgId());
        accountTicket.setBookingId(accountTicket.getBookingId());
        accountTicket.setGuestId(accountTicket.getGuestId());
        accountTicket.setAccountHeadId(accountTicket.getAccountHeadId());
        accountTicket.setTicketIdentifier(accountTicket.getTicketIdentifier());
        accountTicket.setTicketNumber(accountTicket.getTicketNumber());
        accountTicket.setTemplateId(accountTicket.getTemplateId());
        accountTicket.setTicketDate(accountTicket.getTicketDate());
        accountTicket.setPeriodFrom(accountTicket.getPeriodFrom());
        accountTicket.setPeriodUpto(accountTicket.getPeriodUpto());
        accountTicket.setTicketNarration(accountTicket.getTicketNarration());
        accountTicket.setItemTotal(accountTicket.getItemTotal());
        accountTicket.setTaxTotal(accountTicket.getTaxTotal());
        accountTicket.setDiscount(accountTicket.getDiscount());
        accountTicket.setNetTotal(accountTicket.getNetTotal());
        accountTicket.setTicketStatus(accountTicket.getTicketStatus());
        accountTicket.setCreatedBy(accountTicket.getCreatedBy());
        accountTicket.setCreationTime(accountTicket.getCreationTime());
        accountTicket.setLastModifiedBy(accountTicket.getLastModifiedBy());
        accountTicket.setLastModifiedTime(accountTicket.getLastModifiedTime());
        accountTicket.setIsDeleted(accountTicket.getIsDeleted());
        accountTicket.setDeletedBy(accountTicket.getDeletedBy());
        accountTicket.setDeletedTime(accountTicket.getDeletedTime());
        accountTicket.setParentId(accountTicket.getParentId());
        return accountTicket;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public List<AccountTicketItemDTO> getAccountTicketItems() {
        return accountTicketItems;
    }

    public void setAccountTicketItems(List<AccountTicketItem> accountTicketItems) {
        this.accountTicketItems = new ArrayList<>();
        accountTicketItems.forEach(accountTicketItem -> {
            this.accountTicketItems.add(new AccountTicketItemDTO(accountTicketItem));
        });
    }

}
