package com.carmel.guestjini.service.model.Booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "g_guest")
@Audited
public class Guest {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "guest_no", columnDefinition = "text")
    private String guestNo;

    @Column(name = "full_name")
    @Length(min = 1, max = 255, message = "Full name length should be between 1 and 255")
    private String fullName;

    @Column(name = "gender")
    private int gender;

    @Column(name = "phone")
    @NotNull(message = "Phone number cannot be null")
    @Length(max = 255)
    private String phone;

    @Column(name = "profile_pic_path", columnDefinition = "text")
    private String profilePicPath;

    @Column(name = "email")
    @NotNull(message = "Email cannot be null")
    @Length(min=5,max = 255, message = "Email length should be between 5 and 255")
    private String email;

    @Column(name = "scheduled_check_in")
    @NotNull(message = "Scheduled Check in Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduledCheckIn;

    @Column(name = "scheduled_check_out")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduledCheckout;

    @Column(name = "actual_check_in")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualCheckIn;

    @Column(name = "actual_check_out")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualCheckout;

    @Column(name = "package_id")
    private String packageId;

    @Column(name = "rent")
    private double rent;

    @Column(name = "rent_unit")
    private int rentUnit;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "discount_value")
    private double discountValue;

    @Column(name = "discount_value_identifier")
    private int discountValueIdentifier;

    @Column(name = "discount_identifier")
    private int discountIdentifier;

    @Column(name = "inventory_group_id")
    private String inventoryId;

    @Column(name = "guest_status")
    private int guestStatus;

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

    public Guest() {
    }

    public Guest(Booking booking) {
        this.booking = booking;
        this.orgId = booking.getOrgId();
        this.clientId = booking.getClientId();
        this.fullName = booking.getFullName();
        this.gender = booking.getGender();
        this.phone = booking.getPhone();
        this.email = booking.getEmail();
        this.scheduledCheckIn = booking.getCheckInTime();
        this.scheduledCheckout = booking.getCheckOutTime();
        this.packageId = booking.getPackageId();
        this.rent = booking.getRent();
        this.rentUnit = booking.getRentUnit();
        this.promoCode = booking.getPromoCode();
        this.discountValue = booking.getDiscountValue();
        this.discountIdentifier = booking.getDiscountIdentifier();
        this.discountValueIdentifier = booking.getDiscountValueIdentifier();
        this.inventoryId = booking.getInventoryId();
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
