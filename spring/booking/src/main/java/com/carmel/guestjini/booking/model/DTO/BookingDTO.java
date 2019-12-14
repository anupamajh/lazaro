package com.carmel.guestjini.booking.model.DTO;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingSource;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookingDTO {

    private String id;
    private String clientId;
    private String orgId;
    private String referenceNo;
    private String fullName;
    private int gender;
    private String phone;
    private String email;
    private Date checkIn;
    private Date checkInTime;
    private Date checkOut;
    private Date checkOutTime;
    private String packageId;
    private double rent;
    private double rentUnit;
    private String promoCode;
    private double discountValue;
    private int discountValueIdentifier;
    private int discountIdentifier;
    private int bookingStatus;
    private String inventoryId;
    private String processId;
    private String approvalStatus;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private BookingSource bookingSource;

    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.clientId = booking.getClientId();
        this.orgId = booking.getOrgId();
        this.referenceNo = booking.getReferenceNo();
        this.fullName = booking.getFullName();
        this.gender = booking.getGender();
        this.phone = booking.getPhone();
        this.email = booking.getEmail();
        this.checkIn = booking.getCheckIn();
        this.checkInTime = booking.getCheckInTime();
        this.checkOut = booking.getCheckOut();
        this.checkOutTime = booking.getCheckOutTime();
        this.packageId = booking.getPackageId();
        this.rent = booking.getRent();
        this.rentUnit = booking.getRentUnit();
        this.promoCode = booking.getPromoCode();
        this.discountValue = booking.getDiscountValue();
        this.discountValueIdentifier = booking.getDiscountValueIdentifier();
        this.discountIdentifier = booking.getDiscountIdentifier();
        this.bookingStatus = booking.getBookingStatus();
        this.inventoryId = booking.getInventoryId();
        this.processId = booking.getProcessId();
        this.approvalStatus = booking.getApprovalStatus();
        this.createdBy = booking.getCreatedBy();
        this.creationTime = booking.getCreationTime();
        this.lastModifiedBy = booking.getLastModifiedBy();
        this.lastModifiedTime = booking.getLastModifiedTime();
        this.isDeleted = booking.getIsDeleted();
        this.deletedBy = booking.getDeletedBy();
        this.deletedTime = booking.getDeletedTime();
        this.bookingSource = booking.getBookingSource();
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

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
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

    public double getRentUnit() {
        return rentUnit;
    }

    public void setRent_unit(double rentUnit) {
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

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
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

    public BookingSource getBookingSource() {
        return bookingSource;
    }

    public void setBookingSource(BookingSource bookingSource) {
        this.bookingSource = bookingSource;
    }
}
