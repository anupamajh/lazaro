package com.carmel.guestjini.Networking.Accounts;

public class AccountTicketItem {
    private String id;
    private String clientId;
    private String orgId;
    private String bookingId;
    private String guestId;
    private String ticketId;
    private Integer lineNo;
    private String itemNarration;
    private Double rate;
    private Double qty;
    private String qtyUnit;
    private Double subTotal;
    private Double taxValue;
    private Integer taxValueIdentifier;
    private Double itemTotal;
    private Integer hasVoucher;
    private String voucherId;
    private Double amountUsed;
    private Double creationTime;

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

    public Integer getLineNo() {
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

    public Integer getHasVoucher() {
        return hasVoucher;
    }

    public void setHasVoucher(Integer hasVoucher) {
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

    public Double getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Double creationTime) {
        this.creationTime = creationTime;
    }
}
