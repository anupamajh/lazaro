package com.carmel.guestjini.accounts.model.DTO;

import java.awt.print.Book;
import java.util.Date;

public class Guest {
    private String id;
    private String clientId;
    private String orgId;
    private Booking booking;
    private String guestNo;
    private String fullName;
    private int gender;
    private String phone;
    private String profilePicPath;
    private String email;
    private Date scheduledCheckIn;
    private Date scheduledCheckout;
    private Date actualCheckIn;
    private Date actualCheckout;
    private String packageId;
    private double rent;
    private int rentUnit;
    private String promoCode;
    private double discountValue;
    private int discountValueIdentifier;
    private int discountIdentifier;
    private String inventoryId;
    private int guestStatus;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getGuestNo() {
        return guestNo;
    }

    public void setGuestNo(String guestNo) {
        this.guestNo = guestNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getScheduledCheckIn() {
        return scheduledCheckIn;
    }

    public void setScheduledCheckIn(Date scheduledCheckIn) {
        this.scheduledCheckIn = scheduledCheckIn;
    }

    public Date getScheduledCheckout() {
        return scheduledCheckout;
    }

    public void setScheduledCheckout(Date scheduledCheckout) {
        this.scheduledCheckout = scheduledCheckout;
    }

    public Date getActualCheckIn() {
        return actualCheckIn;
    }

    public void setActualCheckIn(Date actualCheckIn) {
        this.actualCheckIn = actualCheckIn;
    }

    public Date getActualCheckout() {
        return actualCheckout;
    }

    public void setActualCheckout(Date actualCheckout) {
        this.actualCheckout = actualCheckout;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getRentUnit() {
        return rentUnit;
    }

    public void setRentUnit(int rentUnit) {
        this.rentUnit = rentUnit;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public int getDiscountValueIdentifier() {
        return discountValueIdentifier;
    }

    public void setDiscountValueIdentifier(int discountValueIdentifier) {
        this.discountValueIdentifier = discountValueIdentifier;
    }

    public int getDiscountIdentifier() {
        return discountIdentifier;
    }

    public void setDiscountIdentifier(int discountIdentifier) {
        this.discountIdentifier = discountIdentifier;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getGuestStatus() {
        return guestStatus;
    }

    public void setGuestStatus(int guestStatus) {
        this.guestStatus = guestStatus;
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
