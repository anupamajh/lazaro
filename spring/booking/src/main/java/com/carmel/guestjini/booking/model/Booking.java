package com.carmel.guestjini.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Entity
@Table(name = "g_booking")
@Audited
public class Booking {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "reference_no", columnDefinition = "text")
    private String referenceNo;

    @Column(name = "full_name")
    @Length(min = 1, max = 255, message = "Full name length should be between 1 and 255")
    private String fullName;

    @Column(name = "gender")
    private int gender;

    @Column(name = "phone")
    @NotNull(message = "Phone number cannot be null")
    @Length(max = 255)
    private String phone;

    @Column(name = "email")
    @NotNull(message = "Email cannot be null")
    @Length(min=5,max = 255, message = "Email length should be between 5 and 255")
    private String email;

    @Column(name = "check_in")
    @NotNull(message = "Checkin Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkIn;

    @Column(name = "check_in_time")
    @NotNull(message = "Checkin Time cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkInTime;

    @Column(name = "check_out")
    @NotNull(message = "Checkout Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkOut;

    @Column(name = "check_out_time")
    @NotNull(message = "Checkout Time cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkOutTime;

    @Column(name = "package_id")
    private String packageId;

    @Column(name = "rent")
    private double rent;

    @Column(name = "rent_unit")
    private double rentUnit;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "discount_value")
    private double discountValue;

    @Column(name = "discount_value_identifier")
    private int discountValueIdentifier;

    @Column(name = "discount_identifier")
    private int discountIdentifier;

    @Column(name = "booking_status")
    private int bookingStatus;

    @Column(name = "inventory_group_id")
    private String inventoryId;

    @Column(name = "process_id")
    private String processId;

    @Column(name = "approval_status")
    private String approvalStatus;

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

    @ManyToOne
    @JoinColumn
    private BookingSource bookingSource;


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

    public void setRentUnit(double rentUnit) {
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
