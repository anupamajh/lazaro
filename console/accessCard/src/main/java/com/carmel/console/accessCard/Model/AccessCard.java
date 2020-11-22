package com.carmel.console.accessCard.Model;

import java.util.Date;

public class AccessCard {
    private String idNumber;
    private String imageUrl;
    private String name;
    private String bloodGroup;
    private String contactNumber;
    private String emergencyNumber;
    private String address;
    private String propertyName;
    private String propertyAddressLine1;
    private String propertyAddressLine2;
    private String unit;
    private Date checkoutDate;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAddressLine1() {
        return propertyAddressLine1;
    }

    public void setPropertyAddressLine1(String propertyAddressLine1) {
        this.propertyAddressLine1 = propertyAddressLine1;
    }

    public String getPropertyAddressLine2() {
        return propertyAddressLine2;
    }

    public void setPropertyAddressLine2(String propertyAddressLine2) {
        this.propertyAddressLine2 = propertyAddressLine2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
