package com.carmel.guestjini.inventory.request;


import java.util.List;

public class PaymentRequest {
    private String orderId;
    private String stayPackage;
    private String property;
    private Integer hasBalcony;
    private Integer hasBathRoom;
    private String mobileNumber;
    private String emailAddress;
    private String fullName;
    private Integer gender;
     private String checkInDate;
    private String checkOutDate;
    private int stayDuration;
    private int stayDurationUnit;
    private Double transactionAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStayPackage() {
        return stayPackage;
    }

    public void setStayPackage(String stayPackage) {
        this.stayPackage = stayPackage;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(Integer hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public Integer getHasBathRoom() {
        return hasBathRoom;
    }

    public void setHasBathRoom(Integer hasBathRoom) {
        this.hasBathRoom = hasBathRoom;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getStayDuration() {
        return stayDuration;
    }

    public void setStayDuration(int stayDuration) {
        this.stayDuration = stayDuration;
    }

    public int getStayDurationUnit() {
        return stayDurationUnit;
    }

    public void setStayDurationUnit(int stayDurationUnit) {
        this.stayDurationUnit = stayDurationUnit;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
