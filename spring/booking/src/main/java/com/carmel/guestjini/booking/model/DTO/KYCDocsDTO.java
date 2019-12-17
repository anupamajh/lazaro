package com.carmel.guestjini.booking.model.DTO;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.KYCDocs;

import java.util.Date;

public class KYCDocsDTO {
    private String id;
    private String clientId;
    private String orgId;
    private BookingDTO booking;
    private String guestId;
    private String type;
    private String docTitle;
    private String name;
    private String path;
    private int error;
    private int size;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public KYCDocsDTO(KYCDocs kycDocs) {
        this.id = kycDocs.getId();
        this.clientId = kycDocs.getClientId();
        this.orgId = kycDocs.getOrgId();
        this.booking = new BookingDTO(kycDocs.getBooking());
        this.guestId = kycDocs.getGuestId();
        this.type = kycDocs.getType();
        this.docTitle = kycDocs.getDocTitle();
        this.name = kycDocs.getName();
        this.path = kycDocs.getPath();
        this.error = kycDocs.getError();
        this.size = kycDocs.getSize();
        this.createdBy = kycDocs.getCreatedBy();
        this.creationTime = kycDocs.getCreationTime();
        this.lastModifiedBy = kycDocs.getLastModifiedBy();
        this.lastModifiedTime = kycDocs.getLastModifiedTime();
        this.isDeleted = kycDocs.getIsDeleted();
        this.deletedBy = kycDocs.getDeletedBy();
        this.deletedTime = kycDocs.getDeletedTime();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
