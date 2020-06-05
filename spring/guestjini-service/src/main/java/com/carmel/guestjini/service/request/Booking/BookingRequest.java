package com.carmel.guestjini.service.request.Booking;

public class BookingRequest {
    private String orderId;
    private String stayPackage;
    private String property;
    private int hasBalcony;
    private int hasBathroom;
    private String mobileNumber;
    private String emailAddress;
    private String fullName;
    private int gender;
    private String selectedInventory;
    private String checkinDate;
    private String checkoutDate;
    private String activationLink;

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

    public int getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(int hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public int getHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(int hasBathroom) {
        this.hasBathroom = hasBathroom;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSelectedInventory() {
        return selectedInventory;
    }

    public void setSelectedInventory(String selectedInventory) {
        this.selectedInventory = selectedInventory;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
    }
}
