package com.carmel.guestjini.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "g_guest_kyc")
@Audited
public class KYC {

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

    @Column(name = "guest_id")
    private String guestId;

    @Column(name = "identity_document")
    private int identityDocument;

    @Column(name = "identity_document_other")
    @Length(min = 0, max = 255, message = "Value cannot exceed 255 characters")
    private String identityDocumentOther;

    @Column(name = "arrived_from")
    @Length(min = 0, max = 200, message = "Value cannot exceed 200 characters")
    private String arrivedFrom;

    @Column(name = "permanent_address")
    @Length(min = 0, max = 255, message = "Value cannot exceed 255 characters")
    private String permanentAddress;

    @Column(name = "designation")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String designation;

    @Column(name = "organization")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String organization;

    @Column(name = "birthdate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(name = "anniversary")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anniversary;

    @Column(name = "food_preference")
    private int foodPreference;

    @Column(name = "payment_mode")
    private int PaymentMode;

    @Column(name = "visit_purpose")
    private int visitPurpose;

    @Column(name = "visit_purpose_other")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String visitPurposeOther;

    @Column(name = "emergency_name_1")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String emergencyName1;

    @Column(name = "emergency_phone_1")
    @Length(min = 0, max = 20, message = "Value cannot exceed 20 characters")
    private String emergencyPhone1;

    @Column(name = "emergency_name_2")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String emergencyName2;

    @Column(name = "emergency_phone_2")
    @Length(min = 0, max = 20, message = "Value cannot exceed 20 characters")
    private String emergencyPhone2;

    @Column(name = "nationality")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String nationality;

    @Column(name = "foreigner_driving_licence_number")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String foreignerDrivingLicenseNumber;

    @Column(name = "foreigner_passport_number")
    @Length(min = 0, max = 100, message = "Value cannot exceed 100 characters")
    private String foreignerPassportNumber;

    @Column(name = "foreigner_residence_phone")
    @Length(min = 0, max = 20, message = "Value cannot exceed 20 characters")
    private String foreignerResidencePhone;

    @Column(name = "foreigner_passport_issue_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foreignerPassportIssueDate;

    @Column(name = "foreigner_passport_expiry_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foreignerPassportExpiryDate;

    @Column(name = "foreigner_visa_issue_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foreignerVisaIssueDate;

    @Column(name = "foreigner_visa_expiry_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foreignerVisaExpiryDate;

    @Column(name = "foreigner_india_arrival_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foreignerIndiaArrivalDate;

    @Column(name = "foreigner_india_departure_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date foreignerIndiaDepartureDate;

    @Column(name = "foriegner_proposed_stay_days")
    private int foreignerProposedStayDays;

    @Column(name = "foriegner_is_employed_in_india")
    private int foreignerIsEmployedInIndia;

    @Column(name = "foriegner_employer_details")
    @Length(min = 0, max = 255, message = "Value cannot exceed 255 characters")
    private String foreignerEmployerDetails;

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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public int getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(int identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getIdentityDocumentOther() {
        return identityDocumentOther;
    }

    public void setIdentityDocumentOther(String identityDocumentOther) {
        this.identityDocumentOther = identityDocumentOther;
    }

    public String getArrivedFrom() {
        return arrivedFrom;
    }

    public void setArrivedFrom(String arrivedFrom) {
        this.arrivedFrom = arrivedFrom;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(Date anniversary) {
        this.anniversary = anniversary;
    }

    public int getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(int foodPreference) {
        this.foodPreference = foodPreference;
    }

    public int getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        PaymentMode = paymentMode;
    }

    public int getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(int visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getVisitPurposeOther() {
        return visitPurposeOther;
    }

    public void setVisitPurposeOther(String visitPurposeOther) {
        this.visitPurposeOther = visitPurposeOther;
    }

    public String getEmergencyName1() {
        return emergencyName1;
    }

    public void setEmergencyName1(String emergencyName1) {
        this.emergencyName1 = emergencyName1;
    }

    public String getEmergencyPhone1() {
        return emergencyPhone1;
    }

    public void setEmergencyPhone1(String emergencyPhone1) {
        this.emergencyPhone1 = emergencyPhone1;
    }

    public String getEmergencyName2() {
        return emergencyName2;
    }

    public void setEmergencyName2(String emergencyName2) {
        this.emergencyName2 = emergencyName2;
    }

    public String getEmergencyPhone2() {
        return emergencyPhone2;
    }

    public void setEmergencyPhone2(String emergencyPhone2) {
        this.emergencyPhone2 = emergencyPhone2;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getForeignerDrivingLicenseNumber() {
        return foreignerDrivingLicenseNumber;
    }

    public void setForeignerDrivingLicenseNumber(String foreignerDrivingLicenseNumber) {
        this.foreignerDrivingLicenseNumber = foreignerDrivingLicenseNumber;
    }

    public String getForeignerPassportNumber() {
        return foreignerPassportNumber;
    }

    public void setForeignerPassportNumber(String foreignerPassportNumber) {
        this.foreignerPassportNumber = foreignerPassportNumber;
    }

    public String getForeignerResidencePhone() {
        return foreignerResidencePhone;
    }

    public void setForeignerResidencePhone(String foreignerResidencePhone) {
        this.foreignerResidencePhone = foreignerResidencePhone;
    }

    public Date getForeignerPassportIssueDate() {
        return foreignerPassportIssueDate;
    }

    public void setForeignerPassportIssueDate(Date foreignerPassportIssueDate) {
        this.foreignerPassportIssueDate = foreignerPassportIssueDate;
    }

    public Date getForeignerPassportExpiryDate() {
        return foreignerPassportExpiryDate;
    }

    public void setForeignerPassportExpiryDate(Date foreignerPassportExpiryDate) {
        this.foreignerPassportExpiryDate = foreignerPassportExpiryDate;
    }

    public Date getForeignerVisaIssueDate() {
        return foreignerVisaIssueDate;
    }

    public void setForeignerVisaIssueDate(Date foreignerVisaIssueDate) {
        this.foreignerVisaIssueDate = foreignerVisaIssueDate;
    }

    public Date getForeignerVisaExpiryDate() {
        return foreignerVisaExpiryDate;
    }

    public void setForeignerVisaExpiryDate(Date foreignerVisaExpiryDate) {
        this.foreignerVisaExpiryDate = foreignerVisaExpiryDate;
    }

    public Date getForeignerIndiaArrivalDate() {
        return foreignerIndiaArrivalDate;
    }

    public void setForeignerIndiaArrivalDate(Date foreignerIndiaArrivalDate) {
        this.foreignerIndiaArrivalDate = foreignerIndiaArrivalDate;
    }

    public Date getForeignerIndiaDepartureDate() {
        return foreignerIndiaDepartureDate;
    }

    public void setForeignerIndiaDepartureDate(Date foreignerIndiaDepartureDate) {
        this.foreignerIndiaDepartureDate = foreignerIndiaDepartureDate;
    }

    public int getForeignerProposedStayDays() {
        return foreignerProposedStayDays;
    }

    public void setForeignerProposedStayDays(int foreignerProposedStayDays) {
        this.foreignerProposedStayDays = foreignerProposedStayDays;
    }

    public int getForeignerIsEmployedInIndia() {
        return foreignerIsEmployedInIndia;
    }

    public void setForeignerIsEmployedInIndia(int foreignerIsEmployedInIndia) {
        this.foreignerIsEmployedInIndia = foreignerIsEmployedInIndia;
    }

    public String getForeignerEmployerDetails() {
        return foreignerEmployerDetails;
    }

    public void setForeignerEmployerDetails(String foreignerEmployerDetails) {
        this.foreignerEmployerDetails = foreignerEmployerDetails;
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
