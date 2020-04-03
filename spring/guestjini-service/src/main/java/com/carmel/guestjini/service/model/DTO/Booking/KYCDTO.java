package com.carmel.guestjini.service.model.DTO.Booking;


import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYC;

import java.util.Date;

public class KYCDTO {
    private String id;
    private String clientId;
    private String orgId;
    private BookingDTO booking;
    private String guestId;
    private int identityDocument;
    private String identityDocumentOther;
    private String arrivedFrom;
    private String permanentAddress;
    private String designation;
    private String organization;
    private Date birthDate;
    private Date anniversary;
    private int foodPreference;
    private int PaymentMode;
    private int visitPurpose;
    private String visitPurposeOther;
    private String emergencyName1;
    private String emergencyPhone1;
    private String emergencyName2;
    private String emergencyPhone2;
    private String nationality;
    private String foreignerDrivingLicenseNumber;
    private String foreignerPassportNumber;
    private String foreignerResidencePhone;
    private Date foreignerPassportIssueDate;
    private Date foreignerPassportExpiryDate;
    private Date foreignerVisaIssueDate;
    private Date foreignerVisaExpiryDate;
    private Date foreignerIndiaArrivalDate;
    private Date foreignerIndiaDepartureDate;
    private int foreignerProposedStayDays;
    private int foreignerIsEmployedInIndia;
    private String foreignerEmployerDetails;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public KYCDTO() {
    }

    public KYCDTO(KYC kyc) {
        this.id = kyc.getId();
        this.clientId = kyc.getClientId();
        this.orgId = kyc.getOrgId();
        this.booking = new BookingDTO(kyc.getBooking());
        this.guestId = kyc.getGuestId();
        this.identityDocument = kyc.getIdentityDocument();
        this.identityDocumentOther = kyc.getIdentityDocumentOther();
        this.arrivedFrom = kyc.getArrivedFrom();
        this.permanentAddress = kyc.getPermanentAddress();
        this.designation = kyc.getDesignation();
        this.organization = kyc.getOrganization();
        this.birthDate = kyc.getBirthDate();
        this.anniversary = kyc.getAnniversary();
        this.foodPreference = kyc.getFoodPreference();
        this.PaymentMode = kyc.getPaymentMode();
        this.visitPurpose = kyc.getVisitPurpose();
        this.visitPurposeOther = kyc.getVisitPurposeOther();
        this.emergencyName1 = kyc.getEmergencyName1();
        this.emergencyPhone1 = kyc.getEmergencyPhone1();
        this.emergencyName2 = kyc.getEmergencyName2();
        this.emergencyPhone2 = kyc.getEmergencyPhone2();
        this.nationality = kyc.getNationality();
        this.foreignerDrivingLicenseNumber = kyc.getForeignerDrivingLicenseNumber();
        this.foreignerPassportNumber = kyc.getForeignerPassportNumber();
        this.foreignerResidencePhone = kyc.getForeignerResidencePhone();
        this.foreignerPassportIssueDate = kyc.getForeignerPassportIssueDate();
        this.foreignerPassportExpiryDate = kyc.getForeignerPassportExpiryDate();
        this.foreignerVisaIssueDate = kyc.getForeignerVisaIssueDate();
        this.foreignerVisaExpiryDate = kyc.getForeignerVisaExpiryDate();
        this.foreignerIndiaArrivalDate = kyc.getForeignerIndiaArrivalDate();
        this.foreignerIndiaDepartureDate = kyc.getForeignerIndiaDepartureDate();
        this.foreignerProposedStayDays = kyc.getForeignerProposedStayDays();
        this.foreignerIsEmployedInIndia = kyc.getForeignerIsEmployedInIndia();
        this.foreignerEmployerDetails = kyc.getForeignerEmployerDetails();
        this.createdBy = kyc.getCreatedBy();
        this.creationTime = kyc.getCreationTime();
        this.lastModifiedBy = kyc.getLastModifiedBy();
        this.lastModifiedTime = kyc.getLastModifiedTime();
        this.isDeleted = kyc.getIsDeleted();
        this.deletedBy = kyc.getDeletedBy();
        this.deletedTime = kyc.getDeletedTime();
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

    public BookingDTO getBooking() {
        return booking;
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
