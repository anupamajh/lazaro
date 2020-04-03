package com.carmel.guestjini.service.model.Accounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "g_account_ticket")
@Audited
public class AccountTicket {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    @Length(max = 40)
    private String clientId;

    @Column(name = "org_id")
    @Length(max = 40)
    private String orgId;

    @Column(name = "booking_id")
    @Length(max = 40)
    private String bookingId;

    @Column(name = "guest_id")
    @Length(max = 40)
    private String guestId;

    @Column(name = "parent_id")
    @Length(max = 40)
    private String parentId;

    @Column(name = "account_head_id")
    @Length(max = 40)
    private String accountHeadId;

    @Column(name = "ticket_identifier")
    private int ticketIdentifier;

    @Column(name = "ticket_no", columnDefinition = "TEXT")
    private String ticketNumber;

    @Column(name = "template_id")
    @Length(max = 40)
    private String templateId;

    @Column(name = "ticket_date")
    @NotNull(message = "Ticket Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ticketDate;

    @Column(name = "period_from")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date periodFrom;

    @Column(name = "period_upto")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date periodUpto;

    @Column(name = "ticket_narration")
    private String ticketNarration;

    @Column(name = "item_total")
    private Double itemTotal;

    @Column(name = "tax_total")
    private Double taxTotal;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "net_total")
    private Double netTotal;

    @Column(name = "ticket_status")
    private int ticketStatus;

    @Column(name = "created_by")
    @Length(max = 40)
    private String createdBy;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_by")
    @Length(max = 40)
    private String lastModifiedBy;

    @Column(name = "last_Modified_time")
    private Date lastModifiedTime;

    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;

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
}
