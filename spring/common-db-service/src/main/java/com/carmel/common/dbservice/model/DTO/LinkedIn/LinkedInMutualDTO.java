package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInMutual;

import java.util.Date;

public class LinkedInMutualDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String mutual;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInMutualDTO() {
    }

    public LinkedInMutualDTO(LinkedInMutual linkedInMutual) {
        this.id = linkedInMutual.getId();
        this.clientId = linkedInMutual.getClientId();
        this.orgId = linkedInMutual.getOrgId();
        this.linkedInId = linkedInMutual.getLinkedInId();
        this.mutual = linkedInMutual.getMutual();
        this.createdBy = linkedInMutual.getCreatedBy();
        this.creationTime = linkedInMutual.getCreationTime();
        this.lastModifiedBy = linkedInMutual.getLastModifiedBy();
        this.lastModifiedTime = linkedInMutual.getLastModifiedTime();
        this.isDeleted = linkedInMutual.getIsDeleted();
        this.deletedBy = linkedInMutual.getDeletedBy();
        this.deletedTime = linkedInMutual.getDeletedTime();
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

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public String getMutual() {
        return mutual;
    }

    public void setMutual(String mutual) {
        this.mutual = mutual;
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
