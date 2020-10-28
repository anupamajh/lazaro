package com.carmel.guestjini.Networking.Tickets;

import java.util.Date;

public class TicketCategory {
    private String id;
    private String clientId;
    private String orgId;
    private String parentId;
    private String categoryDescription;
    private int sequence;
    private int isMessageMandatory;
    private int isPhotoMandatory;
    private int isVideoMandatory;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    private TicketCategory child;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getIsMessageMandatory() {
        return isMessageMandatory;
    }

    public void setIsMessageMandatory(int isMessageMandatory) {
        this.isMessageMandatory = isMessageMandatory;
    }

    public int getIsPhotoMandatory() {
        return isPhotoMandatory;
    }

    public void setIsPhotoMandatory(int isPhotoMandatory) {
        this.isPhotoMandatory = isPhotoMandatory;
    }

    public int getIsVideoMandatory() {
        return isVideoMandatory;
    }

    public void setIsVideoMandatory(int isVideoMandatory) {
        this.isVideoMandatory = isVideoMandatory;
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

    public TicketCategory getChild() {
        return child;
    }

    public void setChild(TicketCategory child) {
        this.child = child;
    }
}
