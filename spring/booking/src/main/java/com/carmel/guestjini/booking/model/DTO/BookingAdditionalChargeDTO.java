package com.carmel.guestjini.booking.model.DTO;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingAdditionalCharge;

import java.util.Date;

public class BookingAdditionalChargeDTO {

    private String id;
    private String clientId;
    private String orgId;
    private String packageId;
    private BookingDTO booking;
    private String guestId;
    private String title;
    private String narration;
    private double amount;
    private int billingCycle;
    private int chargeMethod;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public BookingAdditionalChargeDTO(BookingAdditionalCharge bookingAdditionalCharge) {
        this.id = bookingAdditionalCharge.getId();
        this.clientId = bookingAdditionalCharge.getClientId();
        this.orgId = bookingAdditionalCharge.getOrgId();
        this.packageId = bookingAdditionalCharge.getPackageId();
        this.booking =new BookingDTO( bookingAdditionalCharge.getBooking());
        this.guestId = bookingAdditionalCharge.getGuestId();
        this.title = bookingAdditionalCharge.getTitle();
        this.narration = bookingAdditionalCharge.getNarration();
        this.amount = bookingAdditionalCharge.getAmount();
        this.billingCycle = bookingAdditionalCharge.getBillingCycle();
        this.chargeMethod = bookingAdditionalCharge.getChargeMethod();
        this.createdBy = bookingAdditionalCharge.getCreatedBy();
        this.creationTime = bookingAdditionalCharge.getCreationTime();
        this.lastModifiedBy = bookingAdditionalCharge.getLastModifiedBy();
        this.lastModifiedTime = bookingAdditionalCharge.getLastModifiedTime();
        this.isDeleted = bookingAdditionalCharge.getIsDeleted();
        this.deletedBy = bookingAdditionalCharge.getDeletedBy();
        this.deletedTime = bookingAdditionalCharge.getDeletedTime();
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

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setBooking(Booking booking) {
        this.booking = new BookingDTO(booking);
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(int billingCycle) {
        this.billingCycle = billingCycle;
    }

    public int getChargeMethod() {
        return chargeMethod;
    }

    public void setChargeMethod(int chargeMethod) {
        this.chargeMethod = chargeMethod;
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
