package com.carmel.guestjini.service.model.Accounts;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "g_account_ticket_item")
@Audited
public class AccountTicketItem {

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

    @Column(name = "ticket_id")
    @Length(max = 40)
    private String ticketId;

    @Column(name = "line_no")
    @NotNull(message = "Line number cannot be null")
    private Integer lineNo;

    @Column(name = "item_narration")
    private String itemNarration;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "qty")
    private  Double qty;

    @Column(name = "qty_unit")
    private String qtyUnit;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "tax_value")
    private Double taxValue;

    @Column(name = "tax_value_identifier")
    private  Integer taxValueIdentifier;

    @Column(name = "item_total")
    private Double itemTotal;

    @Column(name = "has_voucher")
    private int hasVoucher;

    @Column(name = "voucher_id")
    private String voucherId;

    @Column(name = "amount_used")
    private Double amountUsed;

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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
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

    public Integer getTaxValueIdentifier() {
        return taxValueIdentifier;
    }

    public void setTaxValueIdentifier(Integer taxValueIdentifier) {
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
